package com.nightfriday.tms.persistence.shipment;

import com.nightfriday.tms.persistence.truck.Truck;
import com.nightfriday.tms.persistence.truckshipment.Truck_Shipment;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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
  @OneToMany(mappedBy = "shipment", orphanRemoval = true, cascade = {CascadeType.MERGE,
      CascadeType.PERSIST})
  private List<Truck_Shipment> trucks = new ArrayList<>();
  // TODO: Add relationships to Shipment and ShippingUnit via join tables

  public Shipment() {
  }

  public String getShipmentNumber() {
    return shipmentNumber;
  }

  public void setShipmentNumber(String shipmentNumber) {
    this.shipmentNumber = shipmentNumber;
  }

  public void setTrucks(Truck truck) {
    Truck_Shipment ts = new Truck_Shipment();
    ts.setTruck(truck);
    ts.setShipment(this);
    this.trucks.add(ts);
  }

  public List<Truck_Shipment> getTrucks() {
    return trucks;
  }
}
