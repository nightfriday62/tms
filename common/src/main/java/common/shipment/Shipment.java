package common.shipment;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.IdGeneratorType;

@Entity
@Table(name = "T_SHIPMENT")
public class Shipment {
  @Id

  private Long shipmentNumber;
}
