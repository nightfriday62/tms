package com.nightfriday.tms.web.shippingUnit;
import com.nightfriday.tms.persistence.shippingUnit.ShippingUnit;

public class ShippingUnitMapper {
    public static ShippingUnitDTO toDTO(ShippingUnit shippingUnit) {
        if (shippingUnit == null) {
        return null;
        }
        ShippingUnitDTO dto = new ShippingUnitDTO();
        dto.setId(shippingUnit.getId());
        dto.setDescription(shippingUnit.getDescription());
        dto.setWeight(shippingUnit.getWeight());
        dto.setLength(shippingUnit.getLength());
        dto.setWidth(shippingUnit.getWidth());
        dto.setHeight(shippingUnit.getHeight());
        dto.setVolume(shippingUnit.getVolume());
        return dto;
    }

    public static ShippingUnit toEntity(ShippingUnitDTO dto) {
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
