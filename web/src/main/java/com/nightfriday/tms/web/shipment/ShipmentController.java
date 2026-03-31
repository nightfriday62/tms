package com.nightfriday.tms.web.shipment;

import com.nightfriday.tms.services.ShipmentService.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ShipmentController {
  @Autowired
  ShipmentService shipmentService;
}
