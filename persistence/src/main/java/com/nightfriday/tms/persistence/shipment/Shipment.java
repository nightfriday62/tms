package com.nightfriday.tms.persistence.shipment;

import com.nightfriday.tms.persistence.shipmentunit.Shipment_ShippingUnit;
import com.nightfriday.tms.persistence.shippingUnit.ShippingUnit;
import com.nightfriday.tms.persistence.truck.Truck;
import com.nightfriday.tms.persistence.truckshipment.Truck_Shipment;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
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
  private LocalDate scheduledShipmentDate;
  private LocalDate actualShipmentDate;
  private LocalDate scheduledDeliveryDate;
  private LocalDate actualDeliveryDate;
  @OneToMany(mappedBy = "shipment", orphanRemoval = true, cascade = {CascadeType.MERGE,
      CascadeType.PERSIST})
  private List<Truck_Shipment> trucks = new ArrayList<>();

  @OneToMany(mappedBy = "shipment", orphanRemoval = true, cascade = {CascadeType.MERGE,
      CascadeType.PERSIST})
  private List<Shipment_ShippingUnit> shippingUnits = new ArrayList<>();

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

  public List<Shipment_ShippingUnit> getShippingUnits() {
    return shippingUnits;
  }
  public void setShippingUnits(ShippingUnit shippingUnit) {
    Shipment_ShippingUnit shipment_ShippingUnit = new Shipment_ShippingUnit();
    shipment_ShippingUnit.setShipment(this);
    shipment_ShippingUnit.setShippingUnit(shippingUnit);
    this.shippingUnits.add(shipment_ShippingUnit);
  }
}
