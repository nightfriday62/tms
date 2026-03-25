package com.nightfriday.tms.persistence.truck;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TruckRepository extends JpaRepository<Truck, String> {

  Optional<Truck> findByPlateNumber(String plateNumber);

  Optional<Truck> findFirstByBrand(String brand);

  @Modifying
  @Query("UPDATE Truck t SET t.active = true, t.deletedAt = null WHERE t.plateNumber = :plateNumber")
  void restoreTruck(@Param("plateNumber") String plateNumber);

  @Query(
      value = "SELECT * FROM T_TRUCK WHERE plate_number = :plateNumber",
      nativeQuery = true
  )
  Truck findDeleted(@Param("plateNumber") String plateNumber);
}
