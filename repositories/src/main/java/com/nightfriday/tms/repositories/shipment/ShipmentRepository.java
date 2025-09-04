package com.nightfriday.tms.repositories.shipment;

import org.springframework.data.repository.Repository;
import com.nightfriday.tms.shipment.Shipment;



public interface ShipmentRepository extends Repository<Shipment, String> {
    Shipment findByShipmentNumber(String shipmentNumber);
    Shipment save(Shipment shipment);

}
