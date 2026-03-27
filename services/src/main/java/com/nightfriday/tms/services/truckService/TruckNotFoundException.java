package com.nightfriday.tms.services.truckService;

public class TruckNotFoundException extends RuntimeException {
  public TruckNotFoundException(String plate) {
    super("Truck " + plate + " not found");
  }
}
