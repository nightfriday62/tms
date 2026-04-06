package com.nightfriday.tms.services.truckService;

import com.nightfriday.tms.persistence.truck.Truck;
import com.nightfriday.tms.persistence.truckshipment.Truck_Shipment;
import java.util.ArrayList;
import java.util.List;

public class TruckMapper {
  static public Truck toEntity(TruckDto truckDto) {
    if  (truckDto == null) {
      return null;
    }
    Truck truck = new Truck();
    truck.setPlateNumber(truckDto.getPlateNumber());
    truck.setTruckType(truckDto.getTruckType());
    truck.setBrand(truckDto.getBrand());
    truck.setModel(truckDto.getModel());
    truck.setVolume(truckDto.getVolume());
    truck.setWeight(truckDto.getWeight());
    truck.setTrailerType(truckDto.getTrailerType());
    truck.setTrailerPlateNumber(truckDto.getTrailerPlateNumber());
    truck.setActive(true);

    return truck;
  }
  static public TruckDto toDto(Truck truck) {
    if (truck == null) {
      return null;
    }
    TruckDto truckDto = new TruckDto();
    truckDto.setPlateNumber(truck.getPlateNumber());
    truckDto.setTruckType(truck.getTruckType());
    truckDto.setBrand(truck.getBrand());
    truckDto.setModel(truck.getModel());
    truckDto.setVolume(truck.getVolume());
    truckDto.setWeight(truck.getWeight());
    truckDto.setTrailerPlateNumber(truck.getTrailerPlateNumber());
    truckDto.setTrailerType(truck.getTrailerType());
    List<String> shipmentNums = new ArrayList<>();
    for (Truck_Shipment truckShipment: truck.getShipments()) {
      shipmentNums.add(truckShipment.getShipment().getShipmentNumber());
    }
    truckDto.setShipments(shipmentNums);
    return truckDto;
  }
}
