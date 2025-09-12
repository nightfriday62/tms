package com.nightfriday.tms.persistence.shippingUnit;

import org.springframework.data.repository.CrudRepository;

public interface ShippingUnitRepository extends CrudRepository<ShippingUnit, Long> {
  ShippingUnit save(ShippingUnit shippingUnit);

  ShippingUnit findById(long id);
  ShippingUnit findByDescription(String description);
}
