package com.nightfriday.tms.persistence.shipment;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "T_SHIPMENT_ID")
public class ShipmentId {
  @Id
  private LocalDate date;
  private String sequence;
  public ShipmentId() {
  }


  public LocalDate getDate() {
    return date;
  }

  public void setDateTime(LocalDate date) {
    this.date = date;
  }

  public String getSequence() {
    return sequence;
  }

  public void setSequence(String sequence) {
    this.sequence = sequence;
  }
  @Override
  public String toString() {
    return date.format(DateTimeFormatter.ofPattern("yyMMdd")) + sequence;
  }
}
