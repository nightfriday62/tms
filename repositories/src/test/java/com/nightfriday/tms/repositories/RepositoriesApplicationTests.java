package com.nightfriday.tms.repositories;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;


@SpringBootTest
class RepositoriesApplicationTests {
  @Autowired
  JdbcTemplate jdbcTemplate;

  @Test
  void contextLoads() {
  }
  @Test
  void testJdbcTemplate() {
    String sql = "select * from T_TRUCK";
    Integer count = jdbcTemplate.queryForObject("select count(*) from T_TRUCK", Integer.class);
    assertThat(count).isEqualTo(1);

  }

}
