package com.nightfriday.tms.services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.nightfriday.tms.persistence", "com.nightfriday.tms.services"})
public class ServicesApplication {

  public static void main(String[] args) {
    SpringApplication.run(ServicesApplication.class, args);
  }

}
