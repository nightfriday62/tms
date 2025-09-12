package com.nightfriday.tms.persistence.truck;


import com.nightfriday.tms.persistence.truckshipment.Truck_Shipment;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.SQLDelete;


@Entity
@Table(name = "T_TRUCK")
@SQLRestriction("active = true")
@SQLDelete(sql = "UPDATE T_TRUCK SET active = false, deleted_at = CURRENT_TIMESTAMP WHERE plate_number = ?")
public class Truck {
  @Id
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
  @OneToMany(mappedBy = "truck", orphanRemoval = true, cascade = {CascadeType.MERGE, CascadeType.PERSIST},fetch = FetchType.EAGER)
  private List<Truck_Shipment> shipments = new ArrayList<>();

  public Truck() {
  }

  public Truck(String plateNumber, TruckType truckType, String brand, String model,
               BigDecimal volume, BigDecimal weight, TrailerType trailerType,
               String trailerPlateNumber) {
    this.plateNumber = plateNumber;
    this.truckType = truckType;
    this.brand = brand;
    this.model = model;
    this.volume = volume;
    this.weight = weight;
    this.trailerType = trailerType;
    this.trailerPlateNumber = trailerPlateNumber;
    this.active = true;
  }

  public String getPlateNumber() {
    return plateNumber;
  }

  public void setPlateNumber(String plateNumber) {
    this.plateNumber = plateNumber;
  }
  public List<Truck_Shipment> getShipments() {
    return this.shipments;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public void setDeletedAt(LocalDateTime deletedAt) {
    this.deletedAt = deletedAt;
  }

  public TruckType getTruckType() {
    return truckType;
  }

  public void setTruckType(TruckType truckType) {
    this.truckType = truckType;
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
}
