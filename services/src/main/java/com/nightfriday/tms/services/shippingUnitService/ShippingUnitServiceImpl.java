package com.nightfriday.tms.services.shippingUnitService;

import com.nightfriday.tms.persistence.shippingUnit.ShippingUnit;
import com.nightfriday.tms.persistence.shippingUnit.ShippingUnitRepository;
import java.util.List;
import java.util.Optional;
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
  public ShippingUnit getShippingUnitById(Long id) {
    Optional<ShippingUnit> unit = shippingUnitRepository.findById(id);
    if (unit.isEmpty()) {
      throw new ShippingUnitNotFoundException(id);
    }
    return unit.orElse(null);
  }

  @Override
    public void deleteShippingUnit(Long id) {
        Optional<ShippingUnit> unit = shippingUnitRepository.findById(id);
        if (unit.isEmpty()) {
        throw new IllegalArgumentException("Shipping unit with id " + id + " not found");
        }
        shippingUnitRepository.delete(unit.get());
    }

  @Override
  public List<ShippingUnit> getShippingUnitByDescription(String description) {
    return shippingUnitRepository.findByDescription(description);
  }
  @Override
    public List<ShippingUnit> searchShippingUnitsByDescription(String description) {
        return shippingUnitRepository.findByDescriptionContainingIgnoreCase(description);
    }
}
