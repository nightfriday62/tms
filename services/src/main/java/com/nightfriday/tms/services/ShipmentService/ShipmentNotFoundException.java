package com.nightfriday.tms.services.ShipmentService;

public class ShipmentNotFoundException extends RuntimeException {
  public ShipmentNotFoundException(String shipmentNumber) {
    super("Shipment: " + shipmentNumber + " not found");
  }
}
