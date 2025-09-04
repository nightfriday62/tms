package com.nightfriday.tms.repositories.shipment;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import com.nightfriday.tms.shipment.Shipment;


@SpringBootTest
@EnableJpaRepositories
@EntityScan(basePackages = "com.nightfriday")
public class ShipmentRepositoryTests {
  @Autowired
  private ShipmentRepository shipmentRepository;

  @Test
  public void testFindByShipmentNumber() {
    // Assuming there's a shipment with shipment number "SHIP123" in the test database
    String shipmentNumber = "SHIP123";
    Shipment shipment = new Shipment();
    shipment.setShipmentNumber(shipmentNumber);
    Shipment returnedShipment = shipmentRepository.save(shipment);
    assertEquals(shipmentNumber, returnedShipment.getShipmentNumber());
    Shipment shipment3 = shipmentRepository.findByShipmentNumber(shipmentNumber);
    assertEquals(shipmentNumber, shipment3.getShipmentNumber());
  }
}
