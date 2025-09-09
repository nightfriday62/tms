package com.nightfriday.tms.persistence.shipment;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "T_SHIPMENT_ID")
public class ShipmentIdTable {
  @Id
  @GeneratedValue
  private Long id;

  private Date date;
  private String shipmentNumber;

  public ShipmentIdTable() {
  }
}
