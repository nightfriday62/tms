package com.nightfriday.tms.persistence.shipment;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ShipmentRepository extends JpaRepository<Shipment, String> {
  Optional<Shipment> findByShipmentNumber(String shipmentNumber);
}
