package com.nightfriday.tms.persistence;

import com.nightfriday.tms.persistence.shipment.Shipment;
import com.nightfriday.tms.persistence.shipment.ShipmentRepository;
import com.nightfriday.tms.persistence.truck.Truck;
import com.nightfriday.tms.persistence.truck.TruckRepository;
import com.nightfriday.tms.persistence.truckshipment.Truck_Shipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TruckTransaction {
  @Autowired
  private TruckRepository truckRepository;
  @Autowired
  private ShipmentRepository shipmentRepository;


  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public Truck saveTruck() {
    Shipment shipment = new Shipment();
    shipment.setShipmentNumber("SHP123456");
    Truck truck = new Truck();
    truck.setPlateNumber("TRK123456");
    truck.setActive(true);
    truckRepository.save(truck);
    Truck_Shipment ts = new Truck_Shipment();
    ts.setTruck(truck);
    ts.setShipment(shipment);
    truck.getShipments().add(ts);
    shipment.getTrucks().add(ts);
    Shipment saved = shipmentRepository.save(shipment);
    return truck;
  }
}
