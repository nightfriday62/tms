package com.nightfriday.tms.services.truckService;

import com.nightfriday.tms.persistence.shipment.Shipment;
import com.nightfriday.tms.persistence.shipment.ShipmentRepository;
import com.nightfriday.tms.persistence.truck.TrailerType;
import com.nightfriday.tms.persistence.truck.Truck;
import com.nightfriday.tms.persistence.truck.TruckRepository;
import com.nightfriday.tms.persistence.truck.TruckType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TruckServiceImpl implements TruckService {

  private final TruckRepository truckRepository;
  private final ShipmentRepository shipmentRepository;

  @Autowired
  public TruckServiceImpl(TruckRepository truckRepository, ShipmentRepository shipmentRepository) {
    this.truckRepository = truckRepository;
    this.shipmentRepository = shipmentRepository;
  }


  @Override
  @Transactional
  public Truck createTruck(String plateNumber, TruckType truckType, String brand, String model,
                           BigDecimal volume, BigDecimal weight, TrailerType trailerType,
                           String trailerPlateNumber, List<String> shipmentNumbers) {

    truckRepository.findByPlateNumber(plateNumber).ifPresent(t -> {
      throw new DuplicateTruckException(plateNumber);
    });
    Truck deletedCheck = truckRepository.findDeleted(plateNumber);
    if (deletedCheck != null) {
      deletedCheck.setActive(true);
      deletedCheck.setDeletedAt(null);
      deletedCheck.setBrand(brand);
      deletedCheck.setModel(model);
      deletedCheck.setTruckType(truckType);
      deletedCheck.setVolume(volume);
      deletedCheck.setWeight(weight);
      deletedCheck.setTrailerType(trailerType);
      deletedCheck.setTrailerPlateNumber(trailerPlateNumber);
      return truckRepository.save(deletedCheck);
    }
    Truck newTruck = new Truck(plateNumber, truckType, brand, model,
        volume,
        weight,
        trailerType, trailerPlateNumber);
    newTruck.setActive(true);
    /*for (String shipmentNumber : shipmentNumbers) {
      shipmentRepository.findByShipmentNumber(shipmentNumber).ifPresent(newTruck::addShipment);
    }
    Just leave this here for learning. newTuck::addShipment is a bound method reference*/
    if (shipmentNumbers != null) {
      List<Shipment> shipments = shipmentRepository.findAllByShipmentNumberIn(shipmentNumbers);
      if (shipments.size() != shipmentNumbers.size()) {
        throw new TruckNotFoundException("One or more shipment numbers not found");
      }
      for (Shipment shipment : shipments) {
        newTruck.addShipment(shipment);
      }
    }
    return truckRepository.save(newTruck);
  }

  @Override
  public Truck createTruck(Truck truck) {

    Truck deletedCheck = truckRepository.findDeleted(truck.getPlateNumber());
    if (deletedCheck != null) {
      deletedCheck.setActive(true);
      deletedCheck.setDeletedAt(null);
      deletedCheck.setBrand(truck.getBrand());
      deletedCheck.setModel(truck.getModel());
      deletedCheck.setTruckType(truck.getTruckType());
      deletedCheck.setVolume(truck.getVolume());
      deletedCheck.setWeight(truck.getWeight());
      deletedCheck.setTrailerType(truck.getTrailerType());
      deletedCheck.setTrailerPlateNumber(truck.getTrailerPlateNumber());
      return truckRepository.save(deletedCheck);
    }
    return truckRepository.save(truck);
  }

  // Soft delete by setting active to false
  // and setting deletedAt to current timestamp
  // Actual deletion is handled by @SQLDelete annotation in Truck entity
  @Override
  public void deleteTruck(String plateNumber) {
    Optional<Truck> truck = truckRepository.findByPlateNumber(plateNumber);
    if (truck.isEmpty()) {
      throw new TruckNotFoundException(plateNumber);
    }
    truckRepository.delete(truck.get());

  }

  @Override
  public Truck getTruckByPlateNumber(String plateNumber) {
    Optional<Truck> truck = truckRepository.findByPlateNumber(plateNumber);
    if (truck.isEmpty()) {
      throw new TruckNotFoundException(plateNumber);
    }
    return truck.get();
  }

  @Override
  @Transactional
  public TruckDto createTruckDto(String plateNumber, TruckType truckType, String brand,
                                 String model, BigDecimal volume, BigDecimal weight,
                                 TrailerType trailerType, String trailerPlateNumber, List<String> shipmentNumbers) {
    Truck truck = createTruck(plateNumber, truckType, brand, model, volume, weight, trailerType, trailerPlateNumber, shipmentNumbers);
    return TruckMapper.toDto(truck);
  }

  @Override
  public TruckDto getTruckByPlateNumberDto(String plateNumber) {
    Truck truck = getTruckByPlateNumber(plateNumber);
    return TruckMapper.toDto(truck);
  }


  @Override
  public List<TruckDto> getTruckDtos() {
    List<Truck> trucks = truckRepository.findAll();
    List<TruckDto> truckDtos = new ArrayList<>();
    for (Truck truck : trucks) {
      truckDtos.add(TruckMapper.toDto(truck));
    }
    return truckDtos;
  }
}
