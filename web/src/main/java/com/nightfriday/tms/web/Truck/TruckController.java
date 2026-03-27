package com.nightfriday.tms.web.Truck;

import com.nightfriday.tms.persistence.truck.Truck;
import com.nightfriday.tms.services.truckService.TruckService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TruckController {
  @Autowired
  TruckService truckService;

  @PostMapping("/trucks")
  @ResponseStatus(HttpStatus.CREATED)
  public TruckDto createTruck(@Valid @RequestBody TruckDto truckDto) {
    Truck truck = TruckMapper.toEntity(truckDto);
    Truck created = truckService.createTruck(truck);
    return TruckMapper.toDto(created);
  }

  @GetMapping("/trucks/{plateNumber}")
  public TruckDto getTruckByPlateNumber(@PathVariable String plateNumber) {
    Truck truck = truckService.getTruckByPlateNumber(plateNumber);
    return TruckMapper.toDto(truck);
  }

  @DeleteMapping("/trucks/{plateNumber}")
  public void deleteTruck(@PathVariable String plateNumber) {
    truckService.deleteTruck(plateNumber);
  }
}
