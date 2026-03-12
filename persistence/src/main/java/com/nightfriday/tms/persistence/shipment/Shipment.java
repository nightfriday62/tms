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
    if (truck == null) return;
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
    if(shippingUnit == null) return;
    Shipment_ShippingUnit shipment_ShippingUnit = new Shipment_ShippingUnit();
    shipment_ShippingUnit.setShipment(this);
    shipment_ShippingUnit.setShippingUnit(shippingUnit);
    this.shippingUnits.add(shipment_ShippingUnit);
  }

  public String getOrigin() {
    return origin;
  }

  public void setOrigin(String origin) {
    this.origin = origin;
  }

  public String getDestination() {
    return destination;
  }

  public void setDestination(String destination) {
    this.destination = destination;
  }

  public BigDecimal getWeight() {
    return weight;
  }

  public void setWeight(BigDecimal weight) {
    this.weight = weight;
  }

  public BigDecimal getVolume() {
    return volume;
  }

  public void setVolume(BigDecimal volume) {
    this.volume = volume;
  }

  public ShipmentStatus getStatus() {
    return status;
  }

  public void setStatus(ShipmentStatus status) {
    this.status = status;
  }

  public LocalDate getScheduledShipmentDate() {
    return scheduledShipmentDate;
  }

  public void setScheduledShipmentDate(LocalDate scheduledShipmentDate) {
    this.scheduledShipmentDate = scheduledShipmentDate;
  }

  public LocalDate getActualShipmentDate() {
    return actualShipmentDate;
  }

  public void setActualShipmentDate(LocalDate actualShipmentDate) {
    this.actualShipmentDate = actualShipmentDate;
  }

  public LocalDate getScheduledDeliveryDate() {
    return scheduledDeliveryDate;
  }

  public void setScheduledDeliveryDate(LocalDate scheduledDeliveryDate) {
    this.scheduledDeliveryDate = scheduledDeliveryDate;
  }

  public LocalDate getActualDeliveryDate() {
    return actualDeliveryDate;
  }

  public void setActualDeliveryDate(LocalDate actualDeliveryDate) {
    this.actualDeliveryDate = actualDeliveryDate;
  }

  public void setTrucks(
      List<Truck_Shipment> trucks) {
    this.trucks = trucks;
  }

  public void setShippingUnits(
      List<Shipment_ShippingUnit> shippingUnits) {
    this.shippingUnits = shippingUnits;
  }
}
