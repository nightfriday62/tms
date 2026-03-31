package com.nightfriday.tms.services.ShipmentService;

import com.nightfriday.tms.persistence.shipment.Shipment;
import com.nightfriday.tms.persistence.shippingUnit.ShippingUnit;
import java.util.List;

public interface ShipmentService {
  Shipment getShipmentById(String shipmentNumber);

  Shipment createShipment(String origin, String destination, double weight,
                          double volume, String scheduledShipmentDate,
                          String scheduledDeliveryDate, List<ShippingUnit> shippingUnits);

  void deleteShipment(String shipmentNumber);

  Shipment addShippingUnitToShipment(String shipmentNumber, ShippingUnit shippingUnit);

  Shipment addTruckToShipment(String shipmentNumber, String plateNumber);


}
