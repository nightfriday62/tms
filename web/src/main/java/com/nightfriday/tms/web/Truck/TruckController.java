package com.nightfriday.tms.web.Truck;


import com.nightfriday.tms.services.truckService.TruckService;
import jakarta.validation.Valid;
import java.util.List;
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
import com.nightfriday.tms.services.truckService.TruckDto;

@RestController
@RequestMapping("/api")
public class TruckController {
  @Autowired
  TruckService truckService;

  @PostMapping("/trucks")
  @ResponseStatus(HttpStatus.CREATED)
  public TruckDto createTruck(@Valid @RequestBody CreateTruckRequest truckRequest) {
    return truckService.createTruckDto(truckRequest.getPlateNumber(), truckRequest.getTruckType(),
        truckRequest.getBrand(),
        truckRequest.getModel(), truckRequest.getVolume(), truckRequest.getWeight(),
        truckRequest.getTrailerType(), truckRequest.getTrailerPlateNumber(),
        truckRequest.getShipments());
  }

  @GetMapping("/trucks/{plateNumber}")
  public TruckDto getTruckByPlateNumber(@PathVariable String plateNumber) {
    return truckService.getTruckByPlateNumberDto(plateNumber);
  }

  @GetMapping("/trucks")
  public List<TruckDto> getTruckDtos() {
    return truckService.getTruckDtos();
  }

  @DeleteMapping("/trucks/{plateNumber}")
  public void deleteTruck(@PathVariable String plateNumber) {
    truckService.deleteTruck(plateNumber);
  }
}
