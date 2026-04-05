package com.nightfriday.tms.services.ShipmentService;

import com.nightfriday.tms.persistence.shipment.Shipment;
import com.nightfriday.tms.persistence.shipmentunit.Shipment_ShippingUnit;
import com.nightfriday.tms.persistence.truckshipment.Truck_Shipment;
import java.util.ArrayList;
import java.util.List;

public class ShipmentMapper {
  static public ShipmentDto mapShipmentToDto(Shipment shipment) {
    ShipmentDto shipmentDto = new ShipmentDto();
    shipmentDto.setShipmentNumber(shipment.getShipmentNumber());
    shipmentDto.setActualDeliveryDate(shipment.getActualDeliveryDate());
    shipmentDto.setActualShipmentDate(shipment.getActualShipmentDate());
    shipmentDto.setDestination(shipment.getDestination());
    shipmentDto.setOrigin(shipment.getOrigin());
    shipmentDto.setScheduledDeliveryDate(shipment.getScheduledDeliveryDate());
    shipmentDto.setScheduledShipmentDate(shipment.getScheduledShipmentDate());
    shipmentDto.setStatus(shipment.getStatus());
    shipmentDto.setVolume(shipment.getVolume());
    shipmentDto.setWeight(shipment.getWeight());
    List<String> trucks = new ArrayList<>();
    for(Truck_Shipment truck : shipment.getTrucks()){
      trucks.add(truck.getTruck().getPlateNumber());
    }
    shipmentDto.setTrucks(trucks);
    List<Long> shippingUnits = new ArrayList<>();
    for(Shipment_ShippingUnit shipment_shippingUnit : shipment.getShippingUnits()){
      shippingUnits.add(shipment_shippingUnit.getShippingUnit().getId());
    }
    shipmentDto.setShippingUnits(shippingUnits);
    return shipmentDto;
  }
}
