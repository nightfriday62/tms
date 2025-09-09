package com.nightfriday.tms.persistence.truck;

import org.springframework.data.repository.CrudRepository;

public interface TruckRepository extends CrudRepository<Truck, String> {
    Truck save(Truck truck);

    Truck findByPlateNumber(String plateNumber);
}
