package com.nightfriday.tms.services;

import static org.assertj.core.api.Assertions.*;

import com.nightfriday.tms.persistence.shipment.Shipment;
import com.nightfriday.tms.persistence.shipment.ShipmentId;
import com.nightfriday.tms.persistence.shipment.ShipmentIdRepository;
import com.nightfriday.tms.persistence.shippingUnit.ShippingUnit;
import com.nightfriday.tms.persistence.truck.Truck;
import com.nightfriday.tms.persistence.truck.TruckRepository;
import com.nightfriday.tms.persistence.truck.TruckType;
import com.nightfriday.tms.services.ShipmentService.ShipmentNotFoundException;
import com.nightfriday.tms.services.ShipmentService.ShipmentService;
import com.nightfriday.tms.services.shippingUnitService.ShippingUnitNotFoundException;
import com.nightfriday.tms.services.shippingUnitService.ShippingUnitService;
import com.nightfriday.tms.services.truckService.TruckNotFoundException;
import com.nightfriday.tms.services.truckService.TruckService;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest(classes = ServicesTestConfig.class)
class ServicesApplicationTests {
  @Autowired
  private TruckService truckService;
  @Autowired
  private TruckRepository truckRepository;
  @Autowired
  private ShippingUnitService shippingUnitService;
  @Autowired
  private ShipmentService shipmentService;
  @Autowired
  private EntityManager em;
  @Autowired
  private ShipmentIdRepository shipmentIdRepository;

  private void inspectShipmentSession(com.nightfriday.tms.persistence.shipment.Shipment shipment) {
    org.hibernate.Session session = em.unwrap(org.hibernate.Session.class);
    System.out.println("session.isOpen() = " + session.isOpen());
    System.out.println("session.isConnected() = " + session.isConnected());
    System.out.println("em.contains(shipment) = " + em.contains(shipment));
    System.out.println("shipment runtime class = " + shipment.getClass().getName());
    Object shippingUnits = shipment.getShippingUnits();
    System.out.println("shippingUnits runtime class = " +
        (shippingUnits == null ? "null" : shippingUnits.getClass().getName()));
    System.out.println("Hibernate.isInitialized(shippingUnits) = " +
        org.hibernate.Hibernate.isInitialized(shippingUnits));
    System.out.println("PUUtil.isLoaded(shippingUnits) = " + em.getEntityManagerFactory()
        .getPersistenceUnitUtil().isLoaded(shipment, "shippingUnits"));

    // Optionally initialize while session is open:
    if (!org.hibernate.Hibernate.isInitialized(shippingUnits)) {
      org.hibernate.Hibernate.initialize(shippingUnits);
      System.out.println("shippingUnits initialized via Hibernate.initialize()");
    }
  }

  @Test
  void contextLoads() {
  }

  @Test
  void testCreateTruck() {
    Truck savedTruck =
        truckService.createTruck("ABC123", TruckType.RIGID, "Volvo", "FH16", BigDecimal.valueOf(20.5), BigDecimal.valueOf(18.0), null,
            null, null);
    Optional<Truck> fetchedTruck = truckRepository.findByPlateNumber("ABC123");
    assertThat(fetchedTruck).isNotNull();
    assertThat(fetchedTruck.get().getPlateNumber()).isEqualTo("ABC123");
    assertThat(fetchedTruck.get().getBrand()).isEqualTo("Volvo");
  }

  @Test
  void testDeleteTruck() {
    truckService.createTruck("DEL123", TruckType.RIGID, "Scania", "R500", BigDecimal.valueOf(25.0), BigDecimal.valueOf(20.0), null,
        null, null );
    Optional<Truck> fetchedTruck = truckRepository.findByPlateNumber("DEL123");
    assertThat(fetchedTruck).isNotNull();
    truckService.deleteTruck("DEL123");
    Optional<Truck> deletedTruck = truckRepository.findByPlateNumber("DEL123");
    assertThat(deletedTruck).isEmpty();
  }

  @Test
  void testDeleteTruck_NotFound() {
    assertThatThrownBy(() -> truckService.deleteTruck("NONEXISTENT"))
        .isInstanceOf(TruckNotFoundException.class)
        .hasMessageContaining("Truck NONEXISTENT not found");
  }

  @Test
  void testCreateShippingUnit() {
    ShippingUnit testUnit = new ShippingUnit("Test Unit", 10.0, 1.0, 1.0, 1.0, 1.0);
    ShippingUnit savedUnit =
        shippingUnitService.createShippingUnit(testUnit);
    ShippingUnit fetchedUnit = shippingUnitService.getShippingUnitById(savedUnit.getId());
    assertThat(fetchedUnit).isNotNull();
    assertThat(fetchedUnit.getDescription()).isEqualTo("Test Unit");
    assertThat(fetchedUnit.getWeight()).isEqualByComparingTo(BigDecimal.valueOf(10.00));
  }

  @Test
  void testDeleteShippingUnit() {
    ShippingUnit testUnit = new ShippingUnit("Delete Unit", 5.0, 0.5, 0.5, 0.5, 0.125);
    ShippingUnit savedUnit =
        shippingUnitService.createShippingUnit(testUnit);
    ShippingUnit fetchedUnit = shippingUnitService.getShippingUnitById(savedUnit.getId());
    assertThat(fetchedUnit).isNotNull();
    shippingUnitService.deleteShippingUnit(savedUnit.getId());
    assertThatThrownBy(() -> shippingUnitService.getShippingUnitById(savedUnit.getId()))
        .isInstanceOf(ShippingUnitNotFoundException.class)
        .hasMessageContaining("Shipping unit with id " + savedUnit.getId() + " not found");
  }

  @Test
  void testGetShippingUnitByDescription() {
    ShippingUnit testUnit = new ShippingUnit("Unique Description", 2.0, 0.2, 0.2, 0.2, 0.008);

    ShippingUnit savedUnit =
        shippingUnitService.createShippingUnit(testUnit);
    List<ShippingUnit> fetchedUnits =
        shippingUnitService.getShippingUnitByDescription("Unique Description");
    assertThat(fetchedUnits).isNotNull();
    assertThat(fetchedUnits.size()).isEqualTo(1);
    assertThat(fetchedUnits.getFirst().getId()).isEqualTo(savedUnit.getId());
    assertThat(fetchedUnits.getFirst().getDescription()).isEqualTo("Unique Description");
  }

  @Test
  void testGetShippingUnitByDescription_NotFound() {
    List<ShippingUnit> found =
        shippingUnitService.getShippingUnitByDescription("Nonexistent Description");
    assertThat(found).isNotNull();
    assertThat(found.size()).isEqualTo(0);

  }

  @Test
  void testSearchShippingUnitsByDescription() {
    ShippingUnit testUnit1 = new ShippingUnit("Searchable Unit One", 1.0, 0.1, 0.1, 0.1, 0.001);
    ShippingUnit testUnit2 = new ShippingUnit("Searchable Unit Two", 2.0, 0.2, 0.2, 0.2, 0.008);
    ShippingUnit testUnit3 = new ShippingUnit("Another Unit", 3.0, 0.3, 0.3, 0.3, 0.027);

    ShippingUnit unit1 =
        shippingUnitService.createShippingUnit(testUnit1);
    ShippingUnit unit2 =
        shippingUnitService.createShippingUnit(testUnit2);
    ShippingUnit unit3 =
        shippingUnitService.createShippingUnit(testUnit3);

    java.util.List<ShippingUnit> results =
        shippingUnitService.searchShippingUnitsByDescription("Searchable");
    assertThat(results).hasSize(2);
    assertThat(results).extracting(ShippingUnit::getId)
        .containsExactlyInAnyOrder(unit1.getId(), unit2.getId());
    List<ShippingUnit> single = shippingUnitService.searchShippingUnitsByDescription("Another");
    assertThat(single).hasSize(1);
    assertThat(single.get(0).getId()).isEqualTo(unit3.getId());
  }

  @Test
  @Transactional
  void testCreateShipment() {
    ShippingUnit testUnit1 = new ShippingUnit("Shipment Unit One", 1.0, 0.1, 0.1, 0.1, 0.001);
    ShippingUnit testUnit2 = new ShippingUnit("Shipment Unit Two", 2.0, 0.2, 0.2, 0.2, 0.008);

    OffsetDateTime scheduled =  OffsetDateTime.now();
    OffsetDateTime delivery = OffsetDateTime.now();
    ShippingUnit unit1 =
        shippingUnitService.createShippingUnit(testUnit1);
    ShippingUnit unit2 =
        shippingUnitService.createShippingUnit(testUnit2);
    Shipment shipment = shipmentService.createShipment("City A", "City B",
        scheduled, delivery, new ArrayList<>(), List.of(unit1.getId(), unit2.getId()));
    assertThat(shipment).isNotNull();
    ShipmentId shipmentId = shipmentIdRepository.findByDate(OffsetDateTime.now(ZoneOffset.UTC).toLocalDate()).orElse(null);
    assertThat(shipment.getShipmentNumber()).isEqualTo(shipmentId.toString());
    assertThat(shipment.getOrigin()).isEqualTo("City A");
    assertThat(shipment.getDestination()).isEqualTo("City B");
    assertThat(shipment.getWeight()).isEqualByComparingTo(BigDecimal.valueOf(3));
    assertThat(shipment.getVolume()).isEqualByComparingTo(BigDecimal.valueOf(0.009));
    assertThat(shipment.getShippingUnits()).hasSize(2);
    assertThat(shipment.getShippingUnits()).extracting(s -> s.getShippingUnit().getId())
        .containsExactlyInAnyOrder(unit1.getId(), unit2.getId());
  }

  @Test
  void testDeleteShipment() {
    OffsetDateTime scheduled =  OffsetDateTime.now();
    OffsetDateTime delivery = OffsetDateTime.now();

    Shipment shipment = shipmentService.createShipment( "City X", "City Y",
        scheduled, delivery, new ArrayList<>(), List.of());
    assertThat(shipment).isNotNull();
    String shipmentNumber = shipment.getShipmentNumber();
    shipmentService.deleteShipment(shipmentNumber);
    assertThatThrownBy(() -> shipmentService.getShipmentById(shipmentNumber))
        .isInstanceOf(ShipmentNotFoundException.class)
        .hasMessageContaining("Shipment: " + shipmentNumber +" not found");
  }

  @Test
  @Transactional
  void testAssignTruckToShipment() {
    OffsetDateTime scheduled =  OffsetDateTime.now();
    OffsetDateTime delivery = OffsetDateTime.now();

    Truck truck =
        truckService.createTruck("TRUCK123", TruckType.RIGID, "MAN", "TGS", BigDecimal.valueOf(25.0), BigDecimal.valueOf(20.0), null, null, null );
    Shipment shipment =
        shipmentService.createShipment( "City C", "City D", scheduled,
            delivery, new ArrayList<>(), List.of());
    String shipmentNumber = shipment.getShipmentNumber();
    Shipment updatedShipment = shipmentService.addTruckToShipment(shipmentNumber, "TRUCK123");
    assertThat(updatedShipment).isNotNull();
    assertThat(updatedShipment.getTrucks()).hasSize(1);
    assertThat(updatedShipment.getTrucks().getFirst().getTruck().getPlateNumber()).isEqualTo(
        "TRUCK123");
  }

  @Test
  @Transactional
  void testAddShippingUnitToShipment() {
    OffsetDateTime scheduled =  OffsetDateTime.now();
    OffsetDateTime delivery = OffsetDateTime.now();

    ShippingUnit testUnit1 = new ShippingUnit("Additional Unit", 1.0, 0.1, 0.1, 0.1, 0.001);
    ShippingUnit unit = shippingUnitService.createShippingUnit(testUnit1);
    Shipment shipment =
        shipmentService.createShipment( "City E", "City F", scheduled,
            delivery, new ArrayList<>(), List.of());
    inspectShipmentSession(shipment);
    ShipmentId shipmentId = shipmentIdRepository.findByDate(OffsetDateTime.now(ZoneOffset.UTC).toLocalDate()).orElse(null);
    Shipment updatedShipment = shipmentService.addShippingUnitToShipment(shipmentId.toString(), unit);
    inspectShipmentSession(shipment);

    assertThat(updatedShipment).isNotNull();
    assertThat(updatedShipment.getShippingUnits()).hasSize(1);
    assertThat(updatedShipment.getShippingUnits().getFirst().getShippingUnit().getId()).isEqualTo(
        unit.getId());
    Shipment shipment2 =
        shipmentService.createShipment( "City E", "City F", scheduled,
            delivery, new ArrayList<>(), List.of());
    ShipmentId shipmentId2 = shipmentIdRepository.findByDate(OffsetDateTime.now(ZoneOffset.UTC).toLocalDate()).orElse(null);
    System.out.println(shipmentId2.getSequence());
    assertThat(shipment2.getShipmentNumber()).isEqualTo(shipmentId2.toString());
  }
}
