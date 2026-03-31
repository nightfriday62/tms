package com.nightfriday.tms.persistence;

import static org.assertj.core.api.Assertions.assertThat;

import com.nightfriday.tms.persistence.shipment.ShipmentId;
import com.nightfriday.tms.persistence.shipment.ShipmentIdRepository;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ShipmentIdTest {
  @Autowired
  private ShipmentIdRepository shipmentIdRepository;

  @Test
  public void save() {
    ShipmentId shipmentId = new ShipmentId();
    LocalDate date = OffsetDateTime.now(ZoneOffset.UTC).toLocalDate();
    String number = "1234";
    shipmentId.setDateTime(date);
    shipmentId.setSequence(number);
    ShipmentId shipmentId1 = shipmentIdRepository.save(shipmentId);
    assertThat(shipmentId1).isNotNull();
    assertThat(shipmentId1.toString()).isNotNull();
    assertThat(shipmentId1.getDate()).isEqualTo(date);
    assertThat(shipmentId1.getSequence()).isEqualTo(number);
  }

  @Test
  public void findByDateTime() {
    ShipmentId shipmentId = new ShipmentId();
    LocalDate date = OffsetDateTime.now(ZoneOffset.UTC).withNano(0).toLocalDate();
    String number = "1234";
    shipmentId.setDateTime(date);
    shipmentId.setSequence(number);
    shipmentIdRepository.save(shipmentId);
    ShipmentId shipmentId1 = shipmentIdRepository.findByDate(date).orElse(null);
    System.out.println(shipmentId1);
    assertThat(shipmentId1).isNotNull();
    assertThat(shipmentId1.toString()).isNotNull();
    assertThat(shipmentId1.getDate()).isEqualTo(date);
    assertThat(shipmentId1.getSequence()).isEqualTo(number);
  }
}
