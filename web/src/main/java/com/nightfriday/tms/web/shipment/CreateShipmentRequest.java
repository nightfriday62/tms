package com.nightfriday.tms.web.shipment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class CreateShipmentRequest {
  @NotBlank
  private String origin;

  @NotBlank
  private String destination;

  @NotNull
  private OffsetDateTime scheduledShipmentDate;

  @NotNull
  private OffsetDateTime scheduledDeliveryDate;

  private List<String> truckPlateNumbers = new ArrayList<>();

  private List<Long> shippingUnitIds = new ArrayList<>();

  public String getOrigin() {
    return origin;
  }

  public void setOrigin(String origin) {
    this.origin = origin;
  }

  public String getDestination() {
    return destination;
  }

  public void setDestination(String destination) {
    this.destination = destination;
  }

  public OffsetDateTime getScheduledShipmentDate() {
    return scheduledShipmentDate;
  }

  public void setScheduledShipmentDate(OffsetDateTime scheduledShipmentDate) {
    this.scheduledShipmentDate = scheduledShipmentDate;
  }

  public OffsetDateTime getScheduledDeliveryDate() {
    return scheduledDeliveryDate;
  }

  public void setScheduledDeliveryDate(OffsetDateTime scheduledDeliveryDate) {
    this.scheduledDeliveryDate = scheduledDeliveryDate;
  }

  public List<String> getTruckPlateNumbers() {
    return truckPlateNumbers;
  }

  public void setTruckPlateNumbers(List<String> truckPlateNumbers) {
    this.truckPlateNumbers = truckPlateNumbers;
  }

  public List<Long> getShippingUnitIds() {
    return shippingUnitIds;
  }

  public void setShippingUnitIds(List<Long> shippingUnitIds) {
    this.shippingUnitIds = shippingUnitIds;
  }
}
