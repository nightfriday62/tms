package com.nightfriday.tms.services.shippingUnitService;

import com.nightfriday.tms.persistence.shippingUnit.ShippingUnit;
import java.util.List;

public interface ShippingUnitService {
  ShippingUnit createShippingUnit(String description, double weight, double height, double width, double length, double volume);
  void deleteShippingUnit(long id);
  ShippingUnit getShippingUnitById(long id);
  ShippingUnit getShippingUnitByDescription(String description);
  List<ShippingUnit> searchShippingUnitsByDescription(String description);
}
