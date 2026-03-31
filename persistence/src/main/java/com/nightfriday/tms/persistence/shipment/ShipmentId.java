package com.nightfriday.tms.persistence.shipment;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
@Table(name = "T_SHIPMENT_ID")
public class ShipmentId {
  @Id
  @GeneratedValue
  private Long id;

  private LocalDate date;
  private String shipmentNumber;
  public ShipmentId() {
  }

  public Long getId() {
    return id;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDateTime(LocalDate date) {
    this.date = date;
  }

  public String getShipmentNumber() {
    return shipmentNumber;
  }

  public void setShipmentNumber(String shipmentNumber) {
    this.shipmentNumber = shipmentNumber;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
