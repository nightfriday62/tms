package com.nightfriday.tms.persistence.shipment;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentIdRepository extends JpaRepository<ShipmentId, Long> {
  Optional<ShipmentId> findByDate(LocalDate date);
}
