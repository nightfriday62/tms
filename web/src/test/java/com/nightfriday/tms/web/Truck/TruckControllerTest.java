package com.nightfriday.tms.web.Truck;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nightfriday.tms.persistence.truck.TrailerType;
import com.nightfriday.tms.persistence.truck.Truck;
import com.nightfriday.tms.persistence.truck.TruckType;
import com.nightfriday.tms.services.truckService.TruckNotFoundException;
import com.nightfriday.tms.services.truckService.TruckService;
import com.nightfriday.tms.web.exception.ApiExceptionHandler;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TruckController.class)
@Import(ApiExceptionHandler.class)
class TruckControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockitoBean
  private TruckService truckService;

  private com.nightfriday.tms.services.truckService.TruckDto validDto;

  @BeforeEach
  void setUp() {
    validDto = new com.nightfriday.tms.services.truckService.TruckDto();
    validDto.setPlateNumber("ABC-123");
    validDto.setTruckType(TruckType.RIGID);
    validDto.setBrand("Volvo");
    validDto.setModel("FH");
    validDto.setVolume(new BigDecimal("55.5"));
    validDto.setWeight(new BigDecimal("18.0"));
    validDto.setTrailerType(TrailerType.BOX);
    validDto.setTrailerPlateNumber("TRL-999");
  }

  @Test
  void testCreateTruck_Success() throws Exception {
    Truck created = buildTruck("ABC-123");

    when(truckService.createTruck(any(Truck.class))).thenReturn(created);

    mockMvc.perform(post("/api/trucks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(validDto)))
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.plateNumber", is("ABC-123")))
        .andExpect(jsonPath("$.truckType", is("RIGID")))
        .andExpect(jsonPath("$.brand", is("Volvo")))
        .andExpect(jsonPath("$.model", is("FH")))
        .andExpect(jsonPath("$.volume", is(55.5)))
        .andExpect(jsonPath("$.weight", is(18.0)))
        .andExpect(jsonPath("$.trailerType", is("BOX")))
        .andExpect(jsonPath("$.trailerPlateNumber", is("TRL-999")));
  }

  @Test
  void testGetTruckByPlateNumber_Success() throws Exception {
    Truck found = buildTruck("ABC-123");

    when(truckService.getTruckByPlateNumber("ABC-123")).thenReturn(found);

    mockMvc.perform(get("/api/trucks/ABC-123").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.plateNumber", is("ABC-123")))
        .andExpect(jsonPath("$.truckType", is("RIGID")))
        .andExpect(jsonPath("$.brand", is("Volvo")));
  }

  @Test
  void testGetTruckByPlateNumber_NotFound() throws Exception {
    when(truckService.getTruckByPlateNumber("MISSING-1"))
        .thenThrow(new TruckNotFoundException("MISSING-1"));

    mockMvc.perform(get("/api/trucks/MISSING-1").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.error", is("NOT_FOUND")))
        .andExpect(jsonPath("$.message", is("Truck MISSING-1 not found")));
  }

  @Test
  void testDeleteTruck_Success() throws Exception {
    mockMvc.perform(delete("/api/trucks/ABC-123").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string(""));

    verify(truckService).deleteTruck("ABC-123");
  }

  @Test
  void testDeleteTruck_NotFound() throws Exception {
    doThrow(new TruckNotFoundException("MISSING-1")).when(truckService).deleteTruck("MISSING-1");

    mockMvc.perform(delete("/api/trucks/MISSING-1").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.error", is("NOT_FOUND")))
        .andExpect(jsonPath("$.message", is("Truck MISSING-1 not found")));
  }

  private Truck buildTruck(String plateNumber) {
    Truck truck = new Truck();
    truck.setPlateNumber(plateNumber);
    truck.setTruckType(TruckType.RIGID);
    truck.setBrand("Volvo");
    truck.setModel("FH");
    truck.setVolume(new BigDecimal("55.5"));
    truck.setWeight(new BigDecimal("18.0"));
    truck.setTrailerType(TrailerType.BOX);
    truck.setTrailerPlateNumber("TRL-999");
    return truck;
  }
}

