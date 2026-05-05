package com.nightfriday.tms.web.config;

import com.nightfriday.tms.persistence.truck.TrailerType;
import com.nightfriday.tms.persistence.truck.TruckType;
import com.nightfriday.tms.services.ShipmentService.ShipmentService;
import com.nightfriday.tms.services.shippingUnitService.ShippingUnitDto;
import com.nightfriday.tms.services.shippingUnitService.ShippingUnitService;
import com.nightfriday.tms.services.truckService.TruckService;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Arrays;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {
  ShippingUnitDto su1;
  ShippingUnitDto su2;
  @Bean
  public ApplicationRunner initData(TruckService truckService, ShippingUnitService shippingUnitService, ShipmentService shipmentService) {
    return args -> {
      truckService.createTruckDto("Test1", TruckType.RIGID, "Volvo", "FH16", BigDecimal.valueOf(20), BigDecimal.valueOf(15), TrailerType.BOX, "TestTrailer1", null);
      truckService.createTruckDto("Test2", TruckType.SEMI, "Scania", "R500", BigDecimal.valueOf(30), BigDecimal.valueOf(25), TrailerType.FLATBED, "TestTrailer2", null);
      ShippingUnitDto su1 = shippingUnitService.createShippingUnitDto("SU1", BigDecimal.valueOf(1), BigDecimal.valueOf(5), BigDecimal.valueOf(2), BigDecimal.valueOf(1), BigDecimal.valueOf(1));
      ShippingUnitDto su2 = shippingUnitService.createShippingUnitDto("SU2", BigDecimal.valueOf(2), BigDecimal.valueOf(10), BigDecimal.valueOf(5), BigDecimal.valueOf(2), BigDecimal.valueOf(2));
      shipmentService.createShipmentDto("SHP1", "New York", OffsetDateTime.now(), OffsetDateTime.now().plusDays(3),
          Arrays.asList("Test1"), Arrays.asList(su1.getId(), su2.getId()));
      shipmentService.createShipmentDto("SHP2", "Chicago", OffsetDateTime.now(), OffsetDateTime.now().plusDays(2),
          Arrays.asList("Test2"), Arrays.asList(su1.getId(), su2.getId()));
    };
  }

  }

