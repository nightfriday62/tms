package com.nightfriday.tms.services.ShipmentService;

import com.nightfriday.tms.persistence.shipment.Shipment;
import com.nightfriday.tms.persistence.shipment.ShipmentId;
import com.nightfriday.tms.persistence.shipment.ShipmentIdRepository;
import com.nightfriday.tms.persistence.shipment.ShipmentRepository;
import com.nightfriday.tms.persistence.shippingUnit.ShippingUnit;
import com.nightfriday.tms.persistence.truck.Truck;
import com.nightfriday.tms.persistence.truck.TruckRepository;
import com.nightfriday.tms.services.truckService.TruckNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShipmentServiceImpl implements ShipmentService {
  @Autowired
  ShipmentRepository shipmentRepository;
  @Autowired
  TruckRepository truckRepository;
  @Autowired
  ShipmentIdRepository shipmentIdRepository;

  @Override
  public Shipment getShipmentById(String shipmentNumber) {
    Optional<Shipment> shipment = shipmentRepository.findByShipmentNumber(shipmentNumber);
    if (shipment.isEmpty()) {
      throw new ShipmentNotFoundException(shipmentNumber);
    }
    return shipment.get();
  }

  @Override
  public Shipment createShipment(String origin, String destination,
                                 double weight,
                                 double volume, String scheduledShipmentDate,
                                 String scheduledDeliveryDate, List<ShippingUnit> shippingUnits) {
    Shipment newShipment = new Shipment();
    newShipment.setShipmentNumber(generateOneShipmentId().toString());
    newShipment.setOrigin(origin);
    newShipment.setDestination(destination);
    newShipment.setWeight(BigDecimal.valueOf(weight));
    newShipment.setVolume(BigDecimal.valueOf(volume));
    newShipment.setScheduledShipmentDate(OffsetDateTime.parse(scheduledShipmentDate));
    newShipment.setScheduledDeliveryDate(OffsetDateTime.parse(scheduledDeliveryDate));
    for (ShippingUnit unit : shippingUnits) {
      newShipment.addShippingUnit(unit);
    }
    return shipmentRepository.save(newShipment);
  }

  @Override
  public void deleteShipment(String shipmentNumber) {
    Optional<Shipment> shipment = shipmentRepository.findByShipmentNumber(shipmentNumber);
    if (shipment.isEmpty()) {
      throw new ShipmentNotFoundException(shipmentNumber);
    }
    shipmentRepository.delete(shipment.get());
  }

  @Override
  public Shipment addShippingUnitToShipment(String shipmentNumber, ShippingUnit shippingUnit) {
    Optional<Shipment> shipment = shipmentRepository.findByShipmentNumber(shipmentNumber);
    if (shipment.isEmpty()) {
      throw new ShipmentNotFoundException(shipmentNumber);
    }
    shipment.get().addShippingUnit(shippingUnit);
    return shipmentRepository.save(shipment.get());
  }

  @Override
  public Shipment addTruckToShipment(String shipmentNumber, String plateNumber) {
    Optional<Truck> truck = truckRepository.findByPlateNumber(plateNumber);
    if (truck.isEmpty()) {
      throw new TruckNotFoundException(plateNumber);
    }
    Optional<Shipment> shipment = shipmentRepository.findByShipmentNumber(shipmentNumber);
    if (shipment.isEmpty()) {
      throw new ShipmentNotFoundException(shipmentNumber);
    }
    shipment.get().assignTruck(truck.get());
    return shipmentRepository.save(shipment.get());
  }
  @Transactional
  private ShipmentId generateOneShipmentId() {
    LocalDate now = OffsetDateTime.now(ZoneOffset.UTC).toLocalDate();
    ShipmentId find = shipmentIdRepository.findByDate(now).orElse(null);
    if (find == null) {
      ShipmentId shipmentId = new ShipmentId();
      shipmentId.setDateTime(now);
      shipmentId.setSequence("0000");
      return shipmentIdRepository.save(shipmentId);
    } else {
        int sequence = Integer.parseInt(find.getSequence());
        sequence++;
        find.setSequence(String.format("%04d", sequence));
        return shipmentIdRepository.save(find);
    }
  }
}
