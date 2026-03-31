package com.nightfriday.tms.persistence.shipment;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

// Not in Use
public class ShipmentIdGenerator implements IdentifierGenerator {
  private static final DateTimeFormatter DATE_PART = DateTimeFormatter.ofPattern("yyyyMMdd");

  @Override
  public Serializable generate(SharedSessionContractImplementor session, Object object) {

    String datePart = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
    int uniquePart = 1234; // This should be replaced with a real unique sequence generator
    return String.format("%s-%04d", datePart, uniquePart);
  }


}
