package com.nightfriday.tms.services.truckService;

public class DuplicateTruckException extends RuntimeException {
  public DuplicateTruckException(String message) {
    super("Truck with plate number " + message + " already exists");
  }
}
