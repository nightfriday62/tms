package com.nightfriday.tms.services.shippingUnitService;
import com.nightfriday.tms.persistence.shippingUnit.ShippingUnit;

public class ShippingUnitMapper {
    public static com.nightfriday.tms.services.shippingUnitService.ShippingUnitDto toDTO(ShippingUnit shippingUnit) {
        if (shippingUnit == null) {
        return null;
        }
      com.nightfriday.tms.services.shippingUnitService.ShippingUnitDto
          dto = new com.nightfriday.tms.services.shippingUnitService.ShippingUnitDto();
        dto.setId(shippingUnit.getId());
        dto.setDescription(shippingUnit.getDescription());
        dto.setWeight(shippingUnit.getWeight());
        dto.setLength(shippingUnit.getLength());
        dto.setWidth(shippingUnit.getWidth());
        dto.setHeight(shippingUnit.getHeight());
        dto.setVolume(shippingUnit.getVolume());
        return dto;
    }

    public static ShippingUnit toEntity(
        com.nightfriday.tms.services.shippingUnitService.ShippingUnitDto dto) {
        if (dto == null) {
        return null;
        }
        ShippingUnit shippingUnit = new ShippingUnit();
        shippingUnit.setDescription(dto.getDescription());
        shippingUnit.setWeight(dto.getWeight());
        shippingUnit.setLength(dto.getLength());
        shippingUnit.setWidth(dto.getWidth());
        shippingUnit.setHeight(dto.getHeight());
        shippingUnit.setVolume(dto.getVolume());
        return shippingUnit;
    }
}
