package com.nightfriday.tms.web.shipment;

import com.nightfriday.tms.persistence.shipment.Shipment;
import com.nightfriday.tms.services.ShipmentService.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
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
  public Shipment createShipment(@RequestBody Shipment shipment) {
    return shipmentService.createShipment(
        shipment.getOrigin(),
        shipment.getDestination(),
        shipment.getScheduledShipmentDate().toString(),
        shipment.getScheduledDeliveryDate().toString(),
        new String[] {"test"}, shipment.getShippingUnits()
    );
  }}
}
