package com.nightfriday.tms.shipment;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "T_SHIPMENT")
public class Shipment {
  @Id
  private String shipmentNumber;
  private String origin;
  private String destination;
  private BigDecimal weight; // in tons
  private BigDecimal volume; // in cubic meters
  private ShipmentStatus status;
  private Date scheduledShipmentDate;
  private Date actualShipmentDate;
  private Date scheduledDeliveryDate;
  private Date actualDeliveryDate;

    public Shipment() {
    }

  public String getShipmentNumber() {
    return shipmentNumber;
  }
    public void setShipmentNumber(String shipmentNumber) {
        this.shipmentNumber = shipmentNumber;
    }
}
