package com.nightfriday.tms.web.shippingUnit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nightfriday.tms.persistence.shippingUnit.ShippingUnit;
import com.nightfriday.tms.services.shippingUnitService.ShippingUnitNotFoundException;
import com.nightfriday.tms.services.shippingUnitService.ShippingUnitService;
import com.nightfriday.tms.web.exception.ApiExceptionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ShippingUnitController.class)
@Import(ApiExceptionHandler.class)
class ShippingUnitControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private ShippingUnitService shippingUnitService;

  @Autowired
  private ObjectMapper objectMapper;

  private com.nightfriday.tms.services.shippingUnitService.ShippingUnitDto validDto;

  @BeforeEach
  void setUp() {
    validDto = new com.nightfriday.tms.services.shippingUnitService.ShippingUnitDto();
    validDto.setDescription("Standard shipping container");
    validDto.setWeight(new BigDecimal("5.5"));
    validDto.setHeight(new BigDecimal("2.5"));
    validDto.setWidth(new BigDecimal("2.0"));
    validDto.setLength(new BigDecimal("6.0"));
    validDto.setVolume(new BigDecimal("30.0"));
  }

  @Test
  void testCreateShippingUnit_Success() throws Exception {
    // Arrange
    ShippingUnit savedEntity = new ShippingUnit();
    ReflectionTestUtils.setField(savedEntity, "id", 1L); // Simulate database-generated ID
    savedEntity.setDescription("Standard shipping container");
    savedEntity.setWeight(new BigDecimal("5.5"));
    savedEntity.setHeight(new BigDecimal("2.5"));
    savedEntity.setWidth(new BigDecimal("2.0"));
    savedEntity.setLength(new BigDecimal("6.0"));
    savedEntity.setVolume(new BigDecimal("30.0"));

    when(shippingUnitService.createShippingUnit(any(ShippingUnit.class)))
        .thenReturn(savedEntity);

    // Act & Assert
    mockMvc
        .perform(
            post("/api/shipping-units")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validDto)))
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id", is(1)))
        .andExpect(jsonPath("$.description", is("Standard shipping container")))
        .andExpect(jsonPath("$.weight", is(5.5)))
        .andExpect(jsonPath("$.height", is(2.5)))
        .andExpect(jsonPath("$.width", is(2.0)))
        .andExpect(jsonPath("$.length", is(6.0)))
        .andExpect(jsonPath("$.volume", is(30.0)));
  }

  @Test
  void testCreateShippingUnit_MissingDescription() throws Exception {
    // Arrange
    com.nightfriday.tms.services.shippingUnitService.ShippingUnitDto
        invalidDto = new com.nightfriday.tms.services.shippingUnitService.ShippingUnitDto();
    invalidDto.setDescription(null); // Missing required field
    invalidDto.setWeight(new BigDecimal("5.5"));
    invalidDto.setHeight(new BigDecimal("2.5"));
    invalidDto.setWidth(new BigDecimal("2.0"));
    invalidDto.setLength(new BigDecimal("6.0"));
    invalidDto.setVolume(new BigDecimal("30.0"));

    // Act & Assert
    mockMvc
        .perform(
            post("/api/shipping-units")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidDto)))
        .andExpect(status().isBadRequest());
  }

  @Test
  void testCreateShippingUnit_DescriptionTooLong() throws Exception {
    // Arrange
    com.nightfriday.tms.services.shippingUnitService.ShippingUnitDto
        invalidDto = new com.nightfriday.tms.services.shippingUnitService.ShippingUnitDto();
    invalidDto.setDescription("x".repeat(256)); // Exceeds max 255 characters
    invalidDto.setWeight(new BigDecimal("5.5"));
    invalidDto.setHeight(new BigDecimal("2.5"));
    invalidDto.setWidth(new BigDecimal("2.0"));
    invalidDto.setLength(new BigDecimal("6.0"));
    invalidDto.setVolume(new BigDecimal("30.0"));

    // Act & Assert
    mockMvc
        .perform(
            post("/api/shipping-units")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidDto)))
        .andExpect(status().isBadRequest());
  }

  @Test
  void testCreateShippingUnit_InvalidWeight() throws Exception {
    // Arrange
    com.nightfriday.tms.services.shippingUnitService.ShippingUnitDto
        invalidDto = new com.nightfriday.tms.services.shippingUnitService.ShippingUnitDto();
    invalidDto.setDescription("Standard shipping container");
    invalidDto.setWeight(new BigDecimal("0")); // Must be > 0
    invalidDto.setHeight(new BigDecimal("2.5"));
    invalidDto.setWidth(new BigDecimal("2.0"));
    invalidDto.setLength(new BigDecimal("6.0"));
    invalidDto.setVolume(new BigDecimal("30.0"));

    // Act & Assert
    mockMvc
        .perform(
            post("/api/shipping-units")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidDto)))
        .andExpect(status().isBadRequest());
  }

  @Test
  void testCreateShippingUnit_InvalidHeight() throws Exception {
    // Arrange
    com.nightfriday.tms.services.shippingUnitService.ShippingUnitDto
        invalidDto = new com.nightfriday.tms.services.shippingUnitService.ShippingUnitDto();
    invalidDto.setDescription("Standard shipping container");
    invalidDto.setWeight(new BigDecimal("5.5"));
    invalidDto.setHeight(new BigDecimal("-1")); // Must be > 0
    invalidDto.setWidth(new BigDecimal("2.0"));
    invalidDto.setLength(new BigDecimal("6.0"));
    invalidDto.setVolume(new BigDecimal("30.0"));

    // Act & Assert
    mockMvc
        .perform(
            post("/api/shipping-units")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidDto)))
        .andExpect(status().isBadRequest());
  }

  @Test
  void testCreateShippingUnit_InvalidWidth() throws Exception {
    // Arrange
    com.nightfriday.tms.services.shippingUnitService.ShippingUnitDto
        invalidDto = new com.nightfriday.tms.services.shippingUnitService.ShippingUnitDto();
    invalidDto.setDescription("Standard shipping container");
    invalidDto.setWeight(new BigDecimal("5.5"));
    invalidDto.setHeight(new BigDecimal("2.5"));
    invalidDto.setWidth(BigDecimal.ZERO); // Must be > 0
    invalidDto.setLength(new BigDecimal("6.0"));
    invalidDto.setVolume(new BigDecimal("30.0"));

    // Act & Assert
    mockMvc
        .perform(
            post("/api/shipping-units")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidDto)))
        .andExpect(status().isBadRequest());
  }

  @Test
  void testCreateShippingUnit_InvalidLength() throws Exception {
    // Arrange
    com.nightfriday.tms.services.shippingUnitService.ShippingUnitDto
        invalidDto = new com.nightfriday.tms.services.shippingUnitService.ShippingUnitDto();
    invalidDto.setDescription("Standard shipping container");
    invalidDto.setWeight(new BigDecimal("5.5"));
    invalidDto.setHeight(new BigDecimal("2.5"));
    invalidDto.setWidth(new BigDecimal("2.0"));
    invalidDto.setLength(new BigDecimal("-0.1")); // Must be > 0
    invalidDto.setVolume(new BigDecimal("30.0"));

    // Act & Assert
    mockMvc
        .perform(
            post("/api/shipping-units")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidDto)))
        .andExpect(status().isBadRequest());
  }

  @Test
  void testCreateShippingUnit_ReturnsCorrectStatus() throws Exception {
    // Arrange
    ShippingUnit savedEntity = new ShippingUnit();
    ReflectionTestUtils.setField(savedEntity, "id", 5L); // Simulate database-generated ID
    savedEntity.setDescription("Test unit");
    savedEntity.setWeight(new BigDecimal("1.0"));
    savedEntity.setHeight(new BigDecimal("1.0"));
    savedEntity.setWidth(new BigDecimal("1.0"));
    savedEntity.setLength(new BigDecimal("1.0"));
    savedEntity.setVolume(new BigDecimal("1.0"));

    when(shippingUnitService.createShippingUnit(any(ShippingUnit.class)))
        .thenReturn(savedEntity);

    // Act
    MvcResult result =
        mockMvc
            .perform(
                post("/api/shipping-units")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(validDto)))
            .andExpect(status().isCreated())
            .andReturn();

    // Assert
    assertThat(result.getResponse().getStatus()).isEqualTo(201);
  }

  @Test
  void testCreateShippingUnit_IdIsGenerated() throws Exception {
    // Arrange - Note: DTO id is null on request
    validDto.setId(null);

    ShippingUnit savedEntity = new ShippingUnit();
    ReflectionTestUtils.setField(savedEntity, "id", 42L); // Simulate database-generated ID
    savedEntity.setDescription("Standard shipping container");
    savedEntity.setWeight(new BigDecimal("5.5"));
    savedEntity.setHeight(new BigDecimal("2.5"));
    savedEntity.setWidth(new BigDecimal("2.0"));
    savedEntity.setLength(new BigDecimal("6.0"));
    savedEntity.setVolume(new BigDecimal("30.0"));

    when(shippingUnitService.createShippingUnit(any(ShippingUnit.class)))
        .thenReturn(savedEntity);

    // Act & Assert
    mockMvc
        .perform(
            post("/api/shipping-units")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validDto)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id", is(42)));
  }

  @Test
  void testCreateShippingUnit_ResponseContainsAllFields() throws Exception {
    // Arrange
    ShippingUnit savedEntity = new ShippingUnit();
    ReflectionTestUtils.setField(savedEntity, "id", 10L); // Simulate database-generated ID
    savedEntity.setDescription("Heavy container");
    savedEntity.setWeight(new BigDecimal("25.75"));
    savedEntity.setHeight(new BigDecimal("3.0"));
    savedEntity.setWidth(new BigDecimal("2.5"));
    savedEntity.setLength(new BigDecimal("7.5"));
    savedEntity.setVolume(new BigDecimal("56.25"));

    when(shippingUnitService.createShippingUnit(any(ShippingUnit.class)))
        .thenReturn(savedEntity);

    // Act & Assert
    mockMvc
        .perform(
            post("/api/shipping-units")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validDto)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").exists())
        .andExpect(jsonPath("$.description").exists())
        .andExpect(jsonPath("$.weight").exists())
        .andExpect(jsonPath("$.height").exists())
        .andExpect(jsonPath("$.width").exists())
        .andExpect(jsonPath("$.length").exists())
        .andExpect(jsonPath("$.volume").exists());
  }

  @Test
  void testGetShippingUnit() throws Exception {

    ShippingUnit savedEntity = new ShippingUnit();
    ReflectionTestUtils.setField(savedEntity, "id", 1L); // Simulate database-generated ID
    savedEntity.setDescription("Standard shipping container");
    savedEntity.setWeight(new BigDecimal("5.5"));
    savedEntity.setHeight(new BigDecimal("2.5"));
    savedEntity.setWidth(new BigDecimal("2.0"));
    savedEntity.setLength(new BigDecimal("6.0"));
    savedEntity.setVolume(new BigDecimal("30.0"));
    when(shippingUnitService.getShippingUnitById(1L)).thenReturn(savedEntity);
    mockMvc.
        perform(
            get("/api/shipping-units/1")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id", is(1)))
        .andExpect(jsonPath("$.description", is("Standard shipping container")))
        .andExpect(jsonPath("$.weight", is(5.5)))
        .andExpect(jsonPath("$.height", is(2.5)))
        .andExpect(jsonPath("$.width", is(2.0)))
        .andExpect(jsonPath("$.length", is(6.0)))
        .andExpect(jsonPath("$.volume", is(30.0)));
  }
  @Test
  void testGetShippingUnit_NoIdFound() throws Exception {
    when(shippingUnitService.getShippingUnitById(1L)).thenThrow(new ShippingUnitNotFoundException(1L));

    mockMvc.perform(
        get("/api/shipping-units/1")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.message", is("Shipping unit with id 1 not found")))
        .andExpect(jsonPath("$.error", is("NOT_FOUND")));

  }
}










