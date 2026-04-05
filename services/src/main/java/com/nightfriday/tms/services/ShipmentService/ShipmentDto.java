package com.nightfriday.tms.services.ShipmentService;

import com.nightfriday.tms.persistence.shipment.ShipmentStatus;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class ShipmentDto {
  private String shipmentNumber;
  private String origin;
  private String destination;
  private BigDecimal weight; // in tons
  private BigDecimal volume; // in cubic meters
  private ShipmentStatus status;
  private OffsetDateTime scheduledShipmentDate;
  private OffsetDateTime actualShipmentDate;
  private OffsetDateTime scheduledDeliveryDate;
  private OffsetDateTime actualDeliveryDate;
  private List<String> trucks = new ArrayList<>();
  private List<Long> shippingUnits = new ArrayList<>();

  public String getShipmentNumber() {
    return shipmentNumber;
  }

  public void setShipmentNumber(String shipmentNumber) {
    this.shipmentNumber = shipmentNumber;
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

  public OffsetDateTime getScheduledShipmentDate() {
    return scheduledShipmentDate;
  }

  public void setScheduledShipmentDate(OffsetDateTime scheduledShipmentDate) {
    this.scheduledShipmentDate = scheduledShipmentDate;
  }

  public OffsetDateTime getActualShipmentDate() {
    return actualShipmentDate;
  }

  public void setActualShipmentDate(OffsetDateTime actualShipmentDate) {
    this.actualShipmentDate = actualShipmentDate;
  }

  public OffsetDateTime getScheduledDeliveryDate() {
    return scheduledDeliveryDate;
  }

  public void setScheduledDeliveryDate(OffsetDateTime scheduledDeliveryDate) {
    this.scheduledDeliveryDate = scheduledDeliveryDate;
  }

  public OffsetDateTime getActualDeliveryDate() {
    return actualDeliveryDate;
  }

  public void setActualDeliveryDate(OffsetDateTime actualDeliveryDate) {
    this.actualDeliveryDate = actualDeliveryDate;
  }

  public List<String> getTrucks() {
    return trucks;
  }

  public void setTrucks(List<String> trucks) {
    this.trucks = trucks;
  }

  public List<Long> getShippingUnits() {
    return shippingUnits;
  }

  public void setShippingUnits(List<Long> shippingUnits) {
    this.shippingUnits = shippingUnits;
  }
}
