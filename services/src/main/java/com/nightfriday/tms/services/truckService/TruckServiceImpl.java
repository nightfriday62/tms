package com.nightfriday.tms.services.truckService;

import com.nightfriday.tms.persistence.truck.TrailerType;
import com.nightfriday.tms.persistence.truck.Truck;
import com.nightfriday.tms.persistence.truck.TruckRepository;
import com.nightfriday.tms.persistence.truck.TruckType;
import java.math.BigDecimal;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TruckServiceImpl implements TruckService {

  private final TruckRepository truckRepository;

  @Autowired
  public TruckServiceImpl(TruckRepository truckRepository) {
    this.truckRepository = truckRepository;
  }


  @Override
  public Truck createTruck(String plateNumber, TruckType truckType, String brand, String model,
                           double volume, double weight, TrailerType trailerType,
                           String trailerPlateNumber) {
    if (plateNumber == null || plateNumber.isEmpty() || truckType == null ||
        volume <= 0 || weight <= 0) {
      throw new IllegalArgumentException("Invalid input parameters");
    }
    Truck deletedCheck = truckRepository.findDeleted(plateNumber);
    if (deletedCheck != null) {
      deletedCheck.setActive(true);
      deletedCheck.setDeletedAt(null);
      deletedCheck.setBrand(brand);
      deletedCheck.setModel(model);
      deletedCheck.setTruckType(truckType);
      deletedCheck.setVolume(BigDecimal.valueOf(volume));
      deletedCheck.setWeight(BigDecimal.valueOf(weight));
      deletedCheck.setTrailerType(trailerType);
      deletedCheck.setTrailerPlateNumber(trailerPlateNumber);
      return truckRepository.save(deletedCheck);
    }
    Truck newTruck = new Truck(plateNumber, truckType, brand, model,
        BigDecimal.valueOf(volume),
        BigDecimal.valueOf(weight),
        trailerType, trailerPlateNumber);
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
}
