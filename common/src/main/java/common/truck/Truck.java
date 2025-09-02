package common.truck;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.lang.Nullable;

@Entity
@Table(name = "T_TRUCK")
public class Truck {
  @Id
  private String plateNumber;
  private TruckType truckType;
  private @Nullable String brand;
  private @Nullable String model;
  private double volume; // in cubic meters
  private double weight;  // in tons
  private @Nullable TrailerType trailerType;
  private @Nullable String trailerPlateNumber;

  public Truck() {
  }
}
