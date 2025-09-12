package com.nightfriday.tms.persistence.truckshipment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface Truck_ShipmentRepository extends JpaRepository<Truck_Shipment, Long> {

  Truck_Shipment findById(long id);
}
