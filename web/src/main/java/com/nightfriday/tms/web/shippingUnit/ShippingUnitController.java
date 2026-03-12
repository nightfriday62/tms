package com.nightfriday.tms.web.shippingUnit;

import com.nightfriday.tms.services.shippingUnitService.ShippingUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ShippingUnitController {
  @Autowired
  private ShippingUnitService shippingUnitService;


}
