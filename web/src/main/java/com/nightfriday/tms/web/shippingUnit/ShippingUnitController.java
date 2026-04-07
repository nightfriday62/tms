package com.nightfriday.tms.web.shippingUnit;

import com.nightfriday.tms.persistence.shippingUnit.ShippingUnit;
import com.nightfriday.tms.services.shippingUnitService.ShippingUnitService;
import java.util.List;
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
import com.nightfriday.tms.services.shippingUnitService.ShippingUnitDto;

@RestController
@RequestMapping("/api")
public class ShippingUnitController {
  @Autowired
  private ShippingUnitService shippingUnitService;
  
  @PostMapping("/shipping-units")
  @ResponseStatus(HttpStatus.CREATED)
  public ShippingUnitDto createShippingUnit(@Valid @RequestBody CreateShippingUnitRequest createShippingUnitRequest) {
    return shippingUnitService.createShippingUnitDto(createShippingUnitRequest.getDescription(),
        createShippingUnitRequest.getWeight(), createShippingUnitRequest.getHeight(),
        createShippingUnitRequest.getWidth(), createShippingUnitRequest.getLength(),
        createShippingUnitRequest.getVolume());
  }

  @GetMapping("/shipping-units/{id}")
  public ShippingUnitDto findShippingUnitById(@PathVariable Long id) {
    return shippingUnitService.getShippingUnitDtoById(id);
  }

  @GetMapping("/shipping-units/find-by-description/{description}")
  public List<ShippingUnitDto> findShippingUnitByDescription(@PathVariable String description) {
    return shippingUnitService.getShippingUnitDtoByDescription(description);
  }

  @GetMapping("/shipping-units/search-by-description/{description}")
  public List<ShippingUnitDto> searchShippingUnitByDescription(@PathVariable String description) {
    return shippingUnitService.searchShippingUnitDtoByDescription(description);
  }
  // TODO: may want to consider using param in search and find endpoints
}
