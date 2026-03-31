package com.nightfriday.tms.services.ShipmentService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nightfriday.tms.persistence.shipment.ShipmentId;
import com.nightfriday.tms.persistence.shipment.ShipmentIdRepository;
import com.nightfriday.tms.persistence.shipment.ShipmentRepository;
import com.nightfriday.tms.persistence.truck.TruckRepository;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
class ShipmentServiceImplTest {

  @Mock
  private ShipmentRepository shipmentRepository;

  @Mock
  private TruckRepository truckRepository;

  @Mock
  private ShipmentIdRepository shipmentIdRepository;

  @InjectMocks
  private ShipmentServiceImpl shipmentService;

  @Test
  void generateOneShipmentId_shouldCreateFirstSequenceWhenDateNotExists() {
    when(shipmentIdRepository.findByDate(any(LocalDate.class))).thenReturn(Optional.empty());
    when(shipmentIdRepository.save(any(ShipmentId.class)))
        .thenAnswer(invocation -> invocation.getArgument(0));

    ShipmentId result = ReflectionTestUtils.invokeMethod(shipmentService, "generateOneShipmentId");

    assertThat(result).isNotNull();
    assertThat(result.getSequence()).isEqualTo("0000");

    ArgumentCaptor<LocalDate> dateCaptor = ArgumentCaptor.forClass(LocalDate.class);
    verify(shipmentIdRepository).findByDate(dateCaptor.capture());
    assertThat(result.getDate()).isEqualTo(dateCaptor.getValue());
    verify(shipmentIdRepository).save(result);
  }

  @Test
  void generateOneShipmentId_shouldIncrementSequenceWhenDateExists() {
    ShipmentId existing = new ShipmentId();
    existing.setDateTime(LocalDate.of(2026, 3, 30));
    existing.setSequence("0007");

    when(shipmentIdRepository.findByDate(any(LocalDate.class))).thenReturn(Optional.of(existing));
    when(shipmentIdRepository.save(any(ShipmentId.class)))
        .thenAnswer(invocation -> invocation.getArgument(0));

    ShipmentId result = ReflectionTestUtils.invokeMethod(shipmentService, "generateOneShipmentId");

    assertThat(result).isNotNull();
    assertThat(result).isSameAs(existing);
    assertThat(result.getSequence()).isEqualTo("0008");
    verify(shipmentIdRepository).save(existing);
  }
}

