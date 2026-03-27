package com.nightfriday.tms.web.Truck;

import com.nightfriday.tms.persistence.truck.TrailerType;
import com.nightfriday.tms.persistence.truck.Truck;
import com.nightfriday.tms.persistence.truck.TruckType;
import com.nightfriday.tms.persistence.truckshipment.Truck_Shipment;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class TruckDto {
  private String plateNumber;
  private TruckType truckType;
  private String brand;
  private String model;
  private BigDecimal volume; // in cubic meters
  private BigDecimal weight;  // in tons
  private TrailerType trailerType;
  private String trailerPlateNumber;
  private boolean active;
  private LocalDateTime deletedAt;
  private List<Truck_Shipment> shipments;
  public TruckDto() {}

  public String getPlateNumber() {
    return plateNumber;
  }

  public void setPlateNumber(String plateNumber) {
    this.plateNumber = plateNumber;
  }

  public TruckType getTruckType() {
    return truckType;
  }

  public void setTruckType(TruckType truckType) {
    this.truckType = truckType;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public BigDecimal getVolume() {
    return volume;
  }

  public void setVolume(BigDecimal volume) {
    this.volume = volume;
  }

  public BigDecimal getWeight() {
    return weight;
  }

  public void setWeight(BigDecimal weight) {
    this.weight = weight;
  }

  public TrailerType getTrailerType() {
    return trailerType;
  }

  public void setTrailerType(TrailerType trailerType) {
    this.trailerType = trailerType;
  }

  public String getTrailerPlateNumber() {
    return trailerPlateNumber;
  }

  public void setTrailerPlateNumber(String trailerPlateNumber) {
    this.trailerPlateNumber = trailerPlateNumber;
  }

  public LocalDateTime getDeletedAt() {
    return deletedAt;
  }

  public void setDeletedAt(LocalDateTime deletedAt) {
    this.deletedAt = deletedAt;
  }

  public List<Truck_Shipment> getShipments() {
    return shipments;
  }

  public void setShipments(
      List<Truck_Shipment> shipments) {
    this.shipments = shipments;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }
}
