package com.nightfriday.tms.services.shippingUnitService;

import com.nightfriday.tms.persistence.shippingUnit.ShippingUnit;
import com.nightfriday.tms.persistence.shippingUnit.ShippingUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShippingUnitServiceImpl implements ShippingUnitService {
  @Autowired
  ShippingUnitRepository shippingUnitRepository;

  @Override
  public ShippingUnit createShippingUnit(ShippingUnit shippingUnit) {
    return shippingUnitRepository.save(shippingUnit);
  }

  @Override
  public ShippingUnit getShippingUnitById(long id) {
    ShippingUnit unit = shippingUnitRepository.findById(id);
    if (unit == null) {
      throw new IllegalArgumentException("Shipping unit with id " + id + " not found");
    }
    return unit;
  }

  @Override
    public void deleteShippingUnit(long id) {
        ShippingUnit unit = shippingUnitRepository.findById(id);
        if (unit == null) {
        throw new IllegalArgumentException("Shipping unit with id " + id + " not found");
        }
        shippingUnitRepository.delete(unit);
    }

  @Override
  public ShippingUnit getShippingUnitByDescription(String description) {
      ShippingUnit unit = shippingUnitRepository.findByDescription(description);
      if (unit == null) {
      throw new IllegalArgumentException("Shipping unit with description " + description + " not found");
      }
      return unit;
  }
  @Override
    public java.util.List<ShippingUnit> searchShippingUnitsByDescription(String description) {
        return shippingUnitRepository.findByDescriptionContainingIgnoreCase(description);
    }
}
