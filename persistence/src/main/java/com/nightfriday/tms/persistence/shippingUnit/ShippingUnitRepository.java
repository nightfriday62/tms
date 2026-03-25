package com.nightfriday.tms.persistence.shippingUnit;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingUnitRepository extends JpaRepository<ShippingUnit, Long> {

  ShippingUnit findById(long id);
  ShippingUnit findByDescription(String description);
  List<ShippingUnit> findByDescriptionContainingIgnoreCase(String description);
}
