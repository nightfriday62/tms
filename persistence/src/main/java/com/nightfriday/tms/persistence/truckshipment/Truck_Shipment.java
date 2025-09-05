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
  private long id;

  @ManyToOne
  @JoinColumn(name = "TRUCK_PLATE_NUMBER", referencedColumnName = "plateNumber", nullable = false)
  private Truck truck;

  @ManyToOne
  @JoinColumn(name = "SHIPMENT_NUMBER", referencedColumnName = "shipmentNumber", nullable = false)
  private Shipment shipment;

  private Date bindingDate;
    public Truck_Shipment() {
    }
}
