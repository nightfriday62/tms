package com.nightfriday.tms.shipment;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;


public class ShipmentIdGenerator implements IdentifierGenerator {

  @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {

        String datePart = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
        int uniquePart = 1234; // This should be replaced with a real unique sequence generator
        return String.format("%s-%04d", datePart, uniquePart);
    }


}
