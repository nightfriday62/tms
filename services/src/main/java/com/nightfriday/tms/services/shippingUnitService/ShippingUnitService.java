package com.nightfriday.tms.services.shippingUnitService;

import com.nightfriday.tms.persistence.shippingUnit.ShippingUnit;
import java.util.List;

public interface ShippingUnitService {
  ShippingUnit createShippingUnit(ShippingUnit shippingUnit);
  void deleteShippingUnit(Long id);
  ShippingUnit getShippingUnitById(Long id);
  List<ShippingUnit> getShippingUnitByDescription(String description);
  List<ShippingUnit> searchShippingUnitsByDescription(String description);
}
