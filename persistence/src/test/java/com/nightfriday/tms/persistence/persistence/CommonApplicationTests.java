package com.nightfriday.tms.persistence.persistence;

import com.nightfriday.tms.persistence.shippingUnit.ShippingUnit;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EnableJpaRepositories
class CommonApplicationTests {
  @Autowired
  private ShippingUnitRepository shippingUnitRepository;
  @Test
  void contextLoads() {
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
        assertEquals(returned.getId(),saved.getId());
        assertEquals(returned.getDescription(),saved.getDescription());
        assertEquals(returned.getWeight(),saved.getWeight());
        assertEquals(returned.getLength(),saved.getLength());
        assertEquals(returned.getHeight(),saved.getHeight());
  }
}
