package com.nightfriday.tms.services.shippingUnitService;

public class ShippingUnitNotFoundException extends RuntimeException {
  public ShippingUnitNotFoundException(Long id) {
    super("Shipping unit with id " + id + " not found");
  }
}
