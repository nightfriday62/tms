package com.nightfriday.tms.services.ShipmentService;

import com.nightfriday.tms.persistence.shipment.Shipment;
import com.nightfriday.tms.persistence.shippingUnit.ShippingUnit;
import java.time.OffsetDateTime;
import java.util.List;

public interface ShipmentService {
  Shipment getShipmentById(String shipmentNumber);
  ShipmentDto getShipmentDtoById(String shipmentId);

  Shipment createShipment(String origin, String destination, OffsetDateTime scheduledShipmentDate,
                          OffsetDateTime scheduledDeliveryDate,List<String> trucks, List<Long> shippingUnits);

  void deleteShipment(String shipmentNumber);

  Shipment addShippingUnitToShipment(String shipmentNumber, ShippingUnit shippingUnit);

  ShipmentDto addShippingUnitToShipmentDto(String shipmentNumber, List<Long> shippingUnitIds);
  ShipmentDto addTrucksToShipmentDto(String shipmentNumber, List<String> platesNumbers);
  Shipment addTruckToShipment(String shipmentNumber, String plateNumber);
  ShipmentDto createShipmentDto(String origin, String destination,
                                OffsetDateTime scheduledShipmentDate,
                                OffsetDateTime scheduledDeliveryDate, List<String> trucks,
                                List<Long> shippingUnits);

}
