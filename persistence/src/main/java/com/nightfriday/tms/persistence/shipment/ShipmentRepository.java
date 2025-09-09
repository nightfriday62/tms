package com.nightfriday.tms.persistence.shipment;

import org.springframework.data.repository.Repository;


public interface ShipmentRepository extends Repository<Shipment, String> {
    Shipment findByShipmentNumber(String shipmentNumber);
    Shipment save(Shipment shipment);

}
