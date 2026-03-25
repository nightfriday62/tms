package com.nightfriday.tms.persistence;

import static org.assertj.core.api.Assertions.assertThat;

import com.nightfriday.tms.persistence.shippingUnit.ShippingUnit;
import com.nightfriday.tms.persistence.shippingUnit.ShippingUnitRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
public class ShippingUnitRepositoryTest {
  @Autowired
  private ShippingUnitRepository shippingUnitRepository;
  private ShippingUnit shippingUnit;

  @BeforeEach
  void setUp() {
    this.shippingUnit = new ShippingUnit("testUnit2", 2.2, 3.3, 4.5, 5.5, 6.6);
  }
  @Test
  void testSave() {
    ShippingUnit shippingUnit1 = new ShippingUnit("testUnit", 2.2, 3.3, 4.5, 5.5, 6.6);
    ShippingUnit saved = shippingUnitRepository.save(shippingUnit1);
    assertThat(saved).isNotNull();
    assertThat(saved.getId()).isNotNull();
    assertThat(saved.getDescription()).isEqualTo("testUnit");
  }
  @Test
  void testFindById() {
    ShippingUnit saved = shippingUnitRepository.save(shippingUnit);
    Long id = saved.getId();
    ShippingUnit shippingUnit1 = shippingUnitRepository.findById(id).get();
    assertThat(shippingUnit1).isNotNull();
    assertThat(shippingUnit1.getDescription()).isEqualTo("testUnit2");
    assertThat(shippingUnit1.getId()).isEqualTo(id);
  }
  @Test
  void testFindByDescription() {
    ShippingUnit saved = shippingUnitRepository.save(shippingUnit);
    List<ShippingUnit> shippingUnits = shippingUnitRepository.findByDescriptionContainingIgnoreCase(saved.getDescription());
    assertThat(shippingUnits).isNotNull();
    assertThat(shippingUnits.size()).isEqualTo(1);
    assertThat(shippingUnits.get(0).getDescription()).isEqualTo(saved.getDescription());

  }
}
