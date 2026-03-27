package com.nightfriday.tms.web.Truck;

import com.nightfriday.tms.persistence.truck.Truck;

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
    truckDto.setShipments(truck.getShipments());
    return truckDto;
  }
}
