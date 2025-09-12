// src/test/java/com/nightfriday/tms/persistence/truck/TruckRepositoryTest.java
package com.nightfriday.tms.persistence;

import com.nightfriday.tms.persistence.truck.Truck;
import com.nightfriday.tms.persistence.truck.TruckRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class TruckRepositoryTest {

  @Autowired
  private TruckRepository truckRepository;


  @Test
  void testFindByPlateNumber() {
    Truck truck = new Truck();
    truck.setPlateNumber("ABC123");
    truck.setActive(true);
    truckRepository.saveAndFlush(truck);

    Truck found = truckRepository.findByPlateNumber("ABC123");
    assertThat(found).isNotNull();
    assertThat(found.getPlateNumber()).isEqualTo("ABC123");
  }

  @Test
  void testFindByBrand() {
    Truck truck = new Truck();
    truck.setPlateNumber("XYZ789");
    truck.setBrand("Volvo");
    truckRepository.saveAndFlush(truck);


    System.out.println(truckRepository.findFirstByBrand("Volvo").getClass());
    /*assertThat(found).isNotNull();
    assertThat(found.getPlateNumber()).isEqualTo("XYZ789");
    assertThat(found.getBrand()).isEqualTo("Volvo");*/
  }
}
