package com.nightfriday.tms.persistence.shipmentunit;

import com.nightfriday.tms.persistence.shipment.Shipment;
import com.nightfriday.tms.persistence.shippingUnit.ShippingUnit;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "T_SHIPMENT_SHIPPING_UNIT")
public class Shipment_ShippingUnit {
  @Id
  @GeneratedValue
  private long id;

  @ManyToOne
  @JoinColumn(name = "SHIPMENT_NUMBER", referencedColumnName = "shipmentNumber", nullable = false)
  private Shipment shipment;

  @ManyToOne
  @JoinColumn(name = "SHIPPING_UNIT_ID", referencedColumnName = "Id", nullable = false)
  private ShippingUnit shippingUnit;

  public Shipment_ShippingUnit() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Shipment getShipment() {
    return shipment;
  }

  public void setShipment(Shipment shipment) {
    this.shipment = shipment;
  }

  public ShippingUnit getShippingUnit() {
    return shippingUnit;
  }

  public void setShippingUnit(ShippingUnit shippingUnit) {
    this.shippingUnit = shippingUnit;
  }
}
