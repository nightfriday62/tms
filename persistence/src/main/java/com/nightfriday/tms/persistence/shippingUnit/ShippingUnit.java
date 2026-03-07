package com.nightfriday.tms.persistence.shippingUnit;

import com.nightfriday.tms.persistence.shipment.Shipment;
import com.nightfriday.tms.persistence.shipmentunit.Shipment_ShippingUnit;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.annotations.Generated;

@Entity(name = "T_SHIPPING_UNIT")
public class ShippingUnit {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String description;
  private BigDecimal weight; // in tons
  private BigDecimal height; // in meters
  private BigDecimal width;  // in meters
  private BigDecimal length; // in meters
  private BigDecimal volume; // in cubic meters

  @OneToMany(mappedBy = "shippingUnit", orphanRemoval = true, cascade = {CascadeType.MERGE,
      CascadeType.PERSIST})
  private List<Shipment_ShippingUnit> shipments = new ArrayList<>();
  public ShippingUnit() {}

  public ShippingUnit(String description, BigDecimal weight, BigDecimal height, BigDecimal width,BigDecimal length, BigDecimal volume) {
    this.description = description;
    this.weight = weight;
    this.height = height;
    this.width = width;
    this.length = length;
    this.volume = volume;
  }

  public ShippingUnit(String description, double weight, double height, double width, double length, double volume) {
    this.description = description;
    this.weight = BigDecimal.valueOf(weight);
    this.height = BigDecimal.valueOf(height);
    this.width = BigDecimal.valueOf(width);
    this.length = BigDecimal.valueOf(length);
    this.volume = BigDecimal.valueOf(volume);
  }

  public long getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public BigDecimal getWeight() {
    return weight;
  }

  public void setWeight(BigDecimal weight) {
    this.weight = weight;
  }

  public BigDecimal getHeight() {
    return height;
  }

  public void setHeight(BigDecimal height) {
    this.height = height;
  }

  public BigDecimal getWidth() {
    return width;
  }

  public void setWidth(BigDecimal width) {
    this.width = width;
  }

  public BigDecimal getLength() {
    return length;
  }

  public void setLength(BigDecimal length) {
    this.length = length;
  }

  public BigDecimal getVolume() {
    return volume;
  }

  public void setVolume(BigDecimal volume) {
    this.volume = volume;
  }
}
