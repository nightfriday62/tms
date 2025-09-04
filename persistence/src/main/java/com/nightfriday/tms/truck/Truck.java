package com.nightfriday.tms.truck;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "T_TRUCK")
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

  public Truck() {
  }
}
