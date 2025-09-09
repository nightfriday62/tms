package com.nightfriday.tms.persistence.shipmentunit;

import org.springframework.data.repository.CrudRepository;

public interface Shipment_ShippingUnitRepository
    extends CrudRepository<Shipment_ShippingUnit, Long> {
  Shipment_ShippingUnit save(Shipment_ShippingUnit shipment_shippingUnit);

  Shipment_ShippingUnit findById(long id);
}
