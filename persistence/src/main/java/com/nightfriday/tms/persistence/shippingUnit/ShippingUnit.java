package com.nightfriday.tms.persistence.shippingUnit;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal;
import org.hibernate.annotations.Generated;

@Entity(name = "T_SHIPPING_UNIT")
public class ShippingUnit {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long Id;
  private String description;
  private BigDecimal weight; // in tons
  private BigDecimal height; // in meters
  private BigDecimal width;  // in meters
  private BigDecimal length; // in meters
  private BigDecimal volume; // in cubic meters
  public ShippingUnit() {}

  public long getId() {
    return Id;
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
