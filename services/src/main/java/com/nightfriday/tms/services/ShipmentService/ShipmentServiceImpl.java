package com.nightfriday.tms.services.ShipmentService;

import com.nightfriday.tms.persistence.shipment.Shipment;
import com.nightfriday.tms.persistence.shipment.ShipmentId;
import com.nightfriday.tms.persistence.shipment.ShipmentIdRepository;
import com.nightfriday.tms.persistence.shipment.ShipmentRepository;
import com.nightfriday.tms.persistence.shippingUnit.ShippingUnit;
import com.nightfriday.tms.persistence.shippingUnit.ShippingUnitRepository;
import com.nightfriday.tms.persistence.truck.Truck;
import com.nightfriday.tms.persistence.truck.TruckRepository;
import com.nightfriday.tms.services.shippingUnitService.ShippingUnitNotFoundException;
import com.nightfriday.tms.services.truckService.TruckNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDate;
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
  @Autowired
  ShippingUnitRepository shippingUnitRepository;

  @Override
  public Shipment getShipmentById(String shipmentNumber) {
    Optional<Shipment> shipment = shipmentRepository.findByShipmentNumber(shipmentNumber);
    if (shipment.isEmpty()) {
      throw new ShipmentNotFoundException(shipmentNumber);
    }
    return shipment.get();
  }

  @Override
  @Transactional
  public Shipment createShipment(String origin, String destination,
                                 OffsetDateTime scheduledShipmentDate,
                                 OffsetDateTime scheduledDeliveryDate, List<String> trucks,
                                 List<Long> shippingUnits) {
    Shipment newShipment = new Shipment();
    newShipment.setShipmentNumber(generateOneShipmentId().toString());
    newShipment.setOrigin(origin);
    newShipment.setDestination(destination);
    newShipment.setScheduledShipmentDate(scheduledShipmentDate);
    newShipment.setScheduledDeliveryDate(scheduledDeliveryDate);
    BigDecimal volume = BigDecimal.ZERO;
    BigDecimal weight = BigDecimal.ZERO;
    List<ShippingUnit> returnedShippingUnits = shippingUnitRepository.findAllById(shippingUnits);
    if (returnedShippingUnits.size() != shippingUnits.size()) {
      throw new ShipmentNotFoundException("Some shipping units not found");
    }
    for (ShippingUnit shippingUnit : returnedShippingUnits) {
      volume = volume.add(shippingUnit.getVolume());
      weight = weight.add(shippingUnit.getWeight());
      newShipment.addShippingUnit(shippingUnit);
    }
    newShipment.setVolume(volume);
    newShipment.setWeight(weight);
    List<Truck> returnedTrucks = truckRepository.findAllById(trucks);
    if (returnedTrucks.size() != trucks.size()) {
      throw new ShipmentNotFoundException("Some trucks not found");
    }
    for (Truck truck : returnedTrucks) {
      newShipment.assignTruck(truck);
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
  @Override
  @Transactional
  public ShipmentDto createShipmentDto(String origin, String destination,
                                       OffsetDateTime scheduledShipmentDate,
                                       OffsetDateTime scheduledDeliveryDate, List<String> trucks,
                                       List<Long> shippingUnits) {
    Shipment shipment = createShipment(origin, destination, scheduledShipmentDate,
        scheduledDeliveryDate, trucks, shippingUnits);
    return ShipmentMapper.mapShipmentToDto(shipment);
  }
  @Override
  @Transactional
  public ShipmentDto getShipmentDtoById(String shipmentId) {
    Optional<Shipment> shipment = shipmentRepository.findByShipmentNumber(shipmentId);
    if (shipment.isEmpty()) {
      throw new ShipmentNotFoundException(shipmentId);
    }
    return ShipmentMapper.mapShipmentToDto(shipment.get());
  }

  @Override
  @Transactional
  public ShipmentDto addShippingUnitToShipmentDto(String shipmentNumber, List<Long> shippingUnitIds) {
    Optional<Shipment> shipment = shipmentRepository.findByShipmentNumber(shipmentNumber);
    if (shipment.isEmpty()) {
      throw new ShipmentNotFoundException(shipmentNumber);
    }
    Shipment updatedShipment = shipment.get();
    List<ShippingUnit> returnedShippingUnits = shippingUnitRepository.findAllById(shippingUnitIds);
    if (returnedShippingUnits.size() != shippingUnitIds.size()) {
      throw new ShippingUnitNotFoundException(-1L);
    }
    for (ShippingUnit shippingUnit : returnedShippingUnits) {
      updatedShipment.addShippingUnit(shippingUnit);
    }
    return ShipmentMapper.mapShipmentToDto(shipmentRepository.save(updatedShipment));
  }

  @Override
  @Transactional
  public ShipmentDto addTrucksToShipmentDto(String shipmentNumber, List<String> platesNumbers) {
    Optional<Shipment> shipment = shipmentRepository.findByShipmentNumber(shipmentNumber);
    if (shipment.isEmpty()) {
      throw new ShipmentNotFoundException(shipmentNumber);
    }
    Shipment updatedShipment = shipment.get();
    List<Truck> returnedTrucks = truckRepository.findAllById(platesNumbers);
    if (returnedTrucks.size() != platesNumbers.size()) {
      throw new TruckNotFoundException("Some trucks not found");
    }
    for (Truck truck : returnedTrucks) {
      updatedShipment.assignTruck(truck);
    }
    return ShipmentMapper.mapShipmentToDto(shipmentRepository.save(updatedShipment));
  }
}
