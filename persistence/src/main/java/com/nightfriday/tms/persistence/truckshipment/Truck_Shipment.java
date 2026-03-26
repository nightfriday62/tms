package com.nightfriday.tms.persistence.truckshipment;

import com.nightfriday.tms.persistence.shipment.Shipment;
import com.nightfriday.tms.persistence.truck.Truck;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "T_TRUCK_SHIPMENT")
public class Truck_Shipment {
  @Id
  @GeneratedValue
  private Long id;

  @ManyToOne
  @JoinColumn(name = "TRUCK_PLATE_NUMBER", referencedColumnName = "plateNumber", nullable = false)
  private Truck truck;

  @ManyToOne
  @JoinColumn(name = "SHIPMENT_NUMBER", referencedColumnName = "shipmentNumber", nullable = false)
  private Shipment shipment;

  private Date bindingDate;

  public Truck_Shipment() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Truck getTruck() {
    return truck;
  }

  public void setTruck(Truck truck) {
    this.truck = truck;
  }

  public Shipment getShipment() {
    return shipment;
  }

  public void setShipment(Shipment shipment) {
    this.shipment = shipment;
  }

  public Date getBindingDate() {
    return bindingDate;
  }

  public void setBindingDate(Date bindingDate) {
    this.bindingDate = bindingDate;
  }
}
