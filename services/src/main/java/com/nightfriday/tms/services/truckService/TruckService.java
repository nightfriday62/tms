package com.nightfriday.tms.services.truckService;

import com.nightfriday.tms.persistence.truck.TrailerType;
import com.nightfriday.tms.persistence.truck.Truck;
import com.nightfriday.tms.persistence.truck.TruckType;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.lang.Nullable;

public interface TruckService {
  Truck createTruck(String plateNumber, TruckType truckType, @Nullable String brand,
                    @Nullable String model, BigDecimal volume, BigDecimal weight, @Nullable TrailerType trailerType, @Nullable String trailerPlateNumber, List<String> shipmentNumbers);
  Truck createTruck(Truck truck);
  void deleteTruck(String plateNumber);
  Truck getTruckByPlateNumber(String plateNumber);
  TruckDto createTruckDto(String plateNumber, TruckType truckType, @Nullable String brand,
                          @Nullable String model, BigDecimal volume, BigDecimal weight, @Nullable TrailerType trailerType, @Nullable String trailerPlateNumber, List<String> shipmentNumbers);
  TruckDto getTruckByPlateNumberDto(String plateNumber);
  List<TruckDto> getTruckDtos();
}
