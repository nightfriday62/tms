package com.nightfriday.tms.services.shippingUnitService;

import com.nightfriday.tms.persistence.shippingUnit.ShippingUnit;
import java.math.BigDecimal;
import java.util.List;

public interface ShippingUnitService {
  ShippingUnit createShippingUnit(ShippingUnit shippingUnit);
  ShippingUnitDto createShippingUnitDto(String description, BigDecimal weight, BigDecimal height, BigDecimal width, BigDecimal length, BigDecimal volume);
  void deleteShippingUnit(Long id);
  ShippingUnit getShippingUnitById(Long id);
  ShippingUnitDto getShippingUnitDtoById(Long id);
  List<ShippingUnit> getShippingUnitByDescription(String description);
  List<ShippingUnitDto> getShippingUnitDtoByDescription(String description);
  List<ShippingUnit> searchShippingUnitsByDescription(String description);
  List<ShippingUnitDto> searchShippingUnitDtoByDescription(String description);
}
