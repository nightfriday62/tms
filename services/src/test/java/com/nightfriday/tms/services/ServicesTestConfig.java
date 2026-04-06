package com.nightfriday.tms.services;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.nightfriday.tms"})
@EnableJpaRepositories("com.nightfriday.tms.persistence")

public class ServicesTestConfig {
}
