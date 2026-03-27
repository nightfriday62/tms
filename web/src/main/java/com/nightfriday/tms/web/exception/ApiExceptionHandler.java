package com.nightfriday.tms.web.exception;

import com.nightfriday.tms.services.ShipmentService.ShipmentNotFoundException;
import com.nightfriday.tms.services.shippingUnitService.ShippingUnitNotFoundException;
import com.nightfriday.tms.services.truckService.TruckNotFoundException;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {
  @ExceptionHandler({ShippingUnitNotFoundException.class, ShipmentNotFoundException.class, TruckNotFoundException.class})
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public Map<String, String> handleNotFound(RuntimeException ex) {
    return Map.of(
        "error", "NOT_FOUND",
        "message", ex.getMessage()
    );
  }
}
