package com.nightfriday.tms.services.truckService;

import com.nightfriday.tms.persistence.truck.TrailerType;
import com.nightfriday.tms.persistence.truck.Truck;
import com.nightfriday.tms.persistence.truck.TruckType;
import org.springframework.lang.Nullable;

public interface TruckService {
  Truck createTruck(String plateNumber, TruckType truckType, @Nullable String brand,
                    @Nullable String model, double volume, double weight, @Nullable TrailerType trailerType, @Nullable String trailerPlateNumber);
  void deleteTruck(String plateNumber);
  Truck getTruckByPlateNumber(String plateNumber);
}
