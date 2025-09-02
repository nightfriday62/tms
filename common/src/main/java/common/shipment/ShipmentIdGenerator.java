package common.shipment;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.EnumSet;
import java.util.concurrent.atomic.AtomicInteger;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.generator.EventType;
import org.hibernate.generator.Generator;
import org.hibernate.id.IdentifierGenerator;


public class ShipmentIdGenerator implements IdentifierGenerator {

  @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {

        String datePart = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
        int uniquePart = uniqueId.getAndIncrement();
        return String.format("%s-%04d", datePart, uniquePart);
    }


}
