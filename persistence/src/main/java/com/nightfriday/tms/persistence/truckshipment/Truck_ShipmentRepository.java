package com.nightfriday.tms.persistence.truckshipment;

import org.springframework.data.repository.CrudRepository;

public interface Truck_ShipmentRepository extends CrudRepository<Truck_Shipment, Long> {
  Truck_Shipment save(Truck_Shipment truck_shipment);

  Truck_Shipment findById(long id);
}
