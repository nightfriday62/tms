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
  public com.nightfriday.tms.services.truckService.TruckDto createTruck(@Valid @RequestBody
                                                                        com.nightfriday.tms.services.truckService.TruckDto truckDto) {
    Truck truck = com.nightfriday.tms.services.truckService.TruckMapper.toEntity(truckDto);
    Truck created = truckService.createTruck(truck);
    return com.nightfriday.tms.services.truckService.TruckMapper.toDto(created);
  }

  @GetMapping("/trucks/{plateNumber}")
  public com.nightfriday.tms.services.truckService.TruckDto getTruckByPlateNumber(@PathVariable String plateNumber) {
    Truck truck = truckService.getTruckByPlateNumber(plateNumber);
    return com.nightfriday.tms.services.truckService.TruckMapper.toDto(truck);
  }

  @DeleteMapping("/trucks/{plateNumber}")
  public void deleteTruck(@PathVariable String plateNumber) {
    truckService.deleteTruck(plateNumber);
  }
}
