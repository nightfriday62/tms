package com.nightfriday.tms.web.shippingUnit;

import java.math.BigDecimal;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.DecimalMin;



public class ShippingUnitDto {
  private Long id;
  @Size(max = 255, message = "Description must be at most 255 characters")
  private String description;
  @DecimalMin(value = "0.0", inclusive = false, message = "Weight must be greater than 0")
  private BigDecimal weight; // in tons
  @DecimalMin(value = "0.0", inclusive = false, message = "Height must be greater than 0")
  private BigDecimal height; // in meters
  @DecimalMin(value = "0.0", inclusive = false, message = "Width must be greater than 0")
  private BigDecimal width;  // in meters
  @DecimalMin(value = "0.0", inclusive = false, message = "Length must be greater than 0")
  private BigDecimal length; // in meters
  private BigDecimal volume; // in cubic meters

  public ShippingUnitDto() {}

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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