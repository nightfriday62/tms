package com.nightfriday.tms.shippingUnit;

import java.math.BigDecimal;

public class ShippingUnit {
  private Long unitId;
  private String description;
  private BigDecimal weight; // in tons
  private BigDecimal height; // in meters
  private BigDecimal width;  // in meters
  private BigDecimal length; // in meters
  private BigDecimal volume; // in cubic meters
}
