package com.nightfriday.tms.persistence;

import com.nightfriday.tms.persistence.shipment.Shipment;
import com.nightfriday.tms.persistence.shipment.ShipmentRepository;
import com.nightfriday.tms.persistence.shippingUnit.ShippingUnit;
import com.nightfriday.tms.persistence.shippingUnit.ShippingUnitRepository;

import com.nightfriday.tms.persistence.truck.Truck;
import com.nightfriday.tms.persistence.truck.TruckRepository;
import com.nightfriday.tms.persistence.truckshipment.Truck_Shipment;
import com.nightfriday.tms.persistence.truckshipment.Truck_ShipmentRepository;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EnableJpaRepositories
class CommonApplicationTests {
  @Autowired
  private ShippingUnitRepository shippingUnitRepository;

  @Autowired
  private EntityManager em;

  @Autowired
  private TruckRepository truckRepository;
  @Autowired
  private ShipmentRepository shipmentRepository;
  @Autowired
  private TruckTransaction truckTransaction;

  @Autowired
  private Truck_ShipmentRepository truck_shipmentRepository;


  @Test
  void contextLoads() {
  }


  @Test
    //@Transactional
  void testTruckCreate() {
    Truck truck = new Truck();
    truck.setPlateNumber("TRK123456");
    truck.setActive(true);
    Truck saved = truckTransaction.saveTruck();
    System.out.println(saved.getPlateNumber());
    Truck returned = truckRepository.findByPlateNumber(saved.getPlateNumber());
    assertEquals(returned.getPlateNumber(), saved.getPlateNumber());
  }

  @Test
  void testCreateShippingUnit() {
    ShippingUnit unit = new ShippingUnit();
    unit.setDescription("This is a test shipping unit");
    unit.setWeight(BigDecimal.valueOf(2.22));
    unit.setLength(BigDecimal.valueOf(3.33));
    unit.setHeight(BigDecimal.valueOf(4.44));
    ShippingUnit saved = shippingUnitRepository.save(unit);
    ShippingUnit returned = shippingUnitRepository.findById(saved.getId());
    assertEquals(returned.getId(), saved.getId());
    assertEquals(returned.getDescription(), saved.getDescription());
    assertEquals(returned.getWeight(), saved.getWeight());
    assertEquals(returned.getLength(), saved.getLength());
    assertEquals(returned.getHeight(), saved.getHeight());
  }

  @Test
  @Transactional
  void testSetCreateShipmentAndBind() {
    Shipment shipment = new Shipment();
    shipment.setShipmentNumber("SHP123456");
    Truck truck = new Truck();
    truck.setPlateNumber("TRK123456");
    truckRepository.save(truck);
    shipment.setTrucks(truck);
    Shipment saved = shipmentRepository.save(shipment);
    Shipment returned = shipmentRepository.findByShipmentNumber(saved.getShipmentNumber());
    assertThat(returned.getShipmentNumber()).isEqualTo("SHP123456");
    assertThat(returned.getTrucks()).hasSize(1);
    assertThat(returned.getTrucks().get(0).getTruck().getPlateNumber()).isEqualTo("TRK123456");
  }

  @Test
  @Transactional
  void testSetShippingUnitAndBind() {
    Shipment shipment = new Shipment();
    shipment.setShipmentNumber("SHP123456");
    ShippingUnit unit = new ShippingUnit();
    unit.setDescription("This is a test shipping unit");
    unit.setWeight(BigDecimal.valueOf(2.22));
    unit.setLength(BigDecimal.valueOf(3.33));
    unit.setHeight(BigDecimal.valueOf(4.44));
    shipment.setShippingUnits(unit);
    shippingUnitRepository.save(unit);
    Shipment saved = shipmentRepository.save(shipment);
    Shipment returned = shipmentRepository.findByShipmentNumber(saved.getShipmentNumber());
    assertThat(returned.getShipmentNumber()).isEqualTo("SHP123456");
    assertThat(returned.getShippingUnits()).hasSize(1);
    assertThat(returned.getShippingUnits().get(0).getShippingUnit().getDescription()).isEqualTo(
        "This is a test shipping unit");

    ShippingUnit shippingUnit =
        shippingUnitRepository.findByDescription("This is a test shipping unit");
    assertThat(shippingUnit.getDescription()).isEqualTo("This is a test shipping unit");
    List<ShippingUnit> shippingUnit1 = shippingUnitRepository.findByDescriptionContainingIgnoreCase("tEst");
    assertThat(shippingUnit1.get(0).getDescription()).isEqualTo("This is a test shipping unit");
  }

  @Test
  @Transactional
  void testDeleteTruckShipmentAndNotDeleteShipment() {
    /*Shipment shipment = new Shipment();
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
    //truckRepository.save(truck); */

    truckTransaction.saveTruck();

    Shipment returned = shipmentRepository.findByShipmentNumber("SHP123456");
    assertThat(returned.getShipmentNumber()).isEqualTo("SHP123456");
    assertThat(returned.getTrucks()).hasSize(1);
    assertThat(returned.getTrucks().get(0).getTruck().getPlateNumber()).isEqualTo("TRK123456");
    System.out.println(returned.getTrucks().get(0).getTruck().getPlateNumber());
    System.out.println(returned.getTrucks().get(0).getId());
    Truck truckAfter = truckRepository.findByPlateNumber("TRK123456");
    System.out.println(truckAfter.getPlateNumber());
    assertThat(truckAfter.getShipments()).hasSize(1);
    assertThat(truck_shipmentRepository.findAll()).hasSize(1);
  }

}
