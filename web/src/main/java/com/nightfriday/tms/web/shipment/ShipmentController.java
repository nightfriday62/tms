package com.nightfriday.tms.web.shipment;

import com.nightfriday.tms.services.ShipmentService.ShipmentDto;
import com.nightfriday.tms.services.ShipmentService.ShipmentService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ShipmentController {
  @Autowired
  ShipmentService shipmentService;

  @PostMapping("/shipments")
  @ResponseStatus(HttpStatus.CREATED)
  public ShipmentDto createShipment(@RequestBody @Valid CreateShipmentRequest request) {
    return shipmentService.createShipmentDto(request.getOrigin(), request.getDestination(),
        request.getScheduledShipmentDate(), request.getScheduledDeliveryDate(),
        request.getTruckPlateNumbers(), request.getShippingUnitIds());
  }

  @GetMapping("/shipments/{shipmentNumber}")
  public ShipmentDto getShipmentById(@PathVariable String shipmentNumber) {
    return shipmentService.getShipmentDtoById(shipmentNumber);
  }

  @DeleteMapping("/shipments/{shipmentNumber}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteShipment(@PathVariable String shipmentNumber) {
    shipmentService.deleteShipment(shipmentNumber);
  }

  @PutMapping("/shipments/{shipmentNumber}/shippingUnits")
  public ShipmentDto addShippingUnitToShipment(@PathVariable String shipmentNumber, @RequestBody
  List<Long> shippingUnitIds) {
    return shipmentService.addShippingUnitToShipmentDto(shipmentNumber, shippingUnitIds);
  }

  @PutMapping("/shipments/{shipmentNumber}/trucks")
  public ShipmentDto addTrucksToShipment(@PathVariable String shipmentNumber, @RequestBody
  List<String> truckPlateNumbers) {
    return shipmentService.addTrucksToShipmentDto(shipmentNumber, truckPlateNumbers);
  }
}
