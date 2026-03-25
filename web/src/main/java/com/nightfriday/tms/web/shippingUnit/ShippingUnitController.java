package com.nightfriday.tms.web.shippingUnit;

import com.nightfriday.tms.persistence.shippingUnit.ShippingUnit;
import com.nightfriday.tms.services.shippingUnitService.ShippingUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/api")
public class ShippingUnitController {
  @Autowired
  private ShippingUnitService shippingUnitService;
  
  @PostMapping("/shipping-units")
  @ResponseStatus(HttpStatus.CREATED)
  public ShippingUnitDto createShippingUnit(@Valid @RequestBody ShippingUnitDto shippingUnitDto) {
    ShippingUnit shippingUnit = ShippingUnitMapper.toEntity(shippingUnitDto);
    ShippingUnit createdShippingUnit = shippingUnitService.createShippingUnit(shippingUnit);
    return ShippingUnitMapper.toDTO(createdShippingUnit);
  }

  @GetMapping("/shipping-units/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ShippingUnitDto findShippingUnitById(@PathVariable long id) {
    ShippingUnit unit = shippingUnitService.getShippingUnitById(id);
    return ShippingUnitMapper.toDTO(unit);
  }
}
