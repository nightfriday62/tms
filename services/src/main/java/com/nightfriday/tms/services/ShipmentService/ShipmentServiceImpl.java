package com.nightfriday.tms.services.ShipmentService;

import com.nightfriday.tms.persistence.shipment.Shipment;
import com.nightfriday.tms.persistence.shipment.ShipmentRepository;
import com.nightfriday.tms.persistence.shippingUnit.ShippingUnit;
import com.nightfriday.tms.persistence.truck.Truck;
import com.nightfriday.tms.persistence.truck.TruckRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShipmentServiceImpl implements ShipmentService {
  @Autowired
  ShipmentRepository shipmentRepository;
  @Autowired
  TruckRepository truckRepository;

  @Override
  public Shipment getShipmentById(String shipmentNumber) {
    Optional<Shipment> shipment = shipmentRepository.findByShipmentNumber(shipmentNumber);
    if (shipment.isEmpty()) {
      throw new IllegalArgumentException("Shipment with number " + shipmentNumber + " not found");
    }
    return shipment.get();
  }

  @Override
  public Shipment createShipment(String shipmentNumber, String origin, String destination,
                                 double weight,
                                 double volume, String scheduledShipmentDate,
                                 String scheduledDeliveryDate, List<ShippingUnit> shippingUnits) {
    Shipment newShipment = new Shipment();
    newShipment.setShipmentNumber(shipmentNumber);
    newShipment.setOrigin(origin);
    newShipment.setDestination(destination);
    newShipment.setWeight(BigDecimal.valueOf(weight));
    newShipment.setVolume(BigDecimal.valueOf(volume));
    newShipment.setScheduledShipmentDate(LocalDate.parse(scheduledShipmentDate));
    newShipment.setScheduledDeliveryDate(LocalDate.parse(scheduledDeliveryDate));
    for (ShippingUnit unit : shippingUnits) {
      newShipment.setShippingUnits(unit);
    }
    return shipmentRepository.save(newShipment);
  }

  @Override
  public void deleteShipment(String shipmentNumber) {
    Optional<Shipment> shipment = shipmentRepository.findByShipmentNumber(shipmentNumber);
    if (shipment.isEmpty()) {
      throw new IllegalArgumentException("Shipment with number " + shipmentNumber + " not found");
    }
    shipmentRepository.delete(shipment.get());
  }

  @Override
  public Shipment addShippingUnitToShipment(String shipmentNumber, ShippingUnit shippingUnit) {
    Optional<Shipment> shipment = shipmentRepository.findByShipmentNumber(shipmentNumber);
    if (shipment.isEmpty()) {
      throw new IllegalArgumentException("Shipment with number " + shipmentNumber + " not found");
    }
    shipment.get().setShippingUnits(shippingUnit);
    return shipmentRepository.save(shipment.get());
  }

  @Override
  public Shipment addTruckToShipment(String shipmentNumber, String plateNumber) {
    Optional<Truck> truck = truckRepository.findByPlateNumber(plateNumber);
    if (truck.isEmpty()) {
      throw new IllegalArgumentException("Truck with plate number " + plateNumber + " not found");
    }
    Optional<Shipment> shipment = shipmentRepository.findByShipmentNumber(shipmentNumber);
    if (shipment.isEmpty()) {
      throw new IllegalArgumentException("Shipment with number " + shipmentNumber + " not found");
    }
    shipment.get().setTrucks(truck.orElse(null));
    return shipmentRepository.save(shipment.get());
  }
}
