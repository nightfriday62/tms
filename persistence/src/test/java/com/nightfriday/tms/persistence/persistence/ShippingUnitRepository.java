package com.nightfriday.tms.persistence.persistence;

import org.springframework.data.repository.CrudRepository;
import com.nightfriday.tms.persistence.shippingUnit.ShippingUnit;

public interface ShippingUnitRepository extends CrudRepository<ShippingUnit, Long> {
  ShippingUnit save(ShippingUnit shippingUnit);

  ShippingUnit findById(long id);
}
