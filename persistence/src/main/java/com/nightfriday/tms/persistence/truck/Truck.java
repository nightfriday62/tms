package com.nightfriday.tms.persistence.truck;


import com.nightfriday.tms.persistence.truckshipment.Truck_Shipment;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
  private double volume; // in cubic meters
  private double weight;  // in tons
  private TrailerType trailerType;
  private String trailerPlateNumber;
  private boolean active;
  private LocalDateTime deletedAt;
  @OneToMany(mappedBy = "truck", orphanRemoval = true, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  private List<Truck_Shipment> shipments = new ArrayList<>();

  public Truck() {
  }

  public String getPlateNumber() {
    return plateNumber;
  }

  public void setPlateNumber(String plateNumber) {
    this.plateNumber = plateNumber;
  }
}
