package com.nightfriday.tms.persistence.shippingUnit;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ShippingUnitRepository extends JpaRepository<ShippingUnit, Long> {
  ShippingUnit save(ShippingUnit shippingUnit);

  ShippingUnit findById(long id);
  ShippingUnit findByDescription(String description);
  List<ShippingUnit> findByDescriptionContainingIgnoreCase(String description);
}
