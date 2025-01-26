package com.enviro.assessment.grad001.lethaboletsoalo.dto;

import com.enviro.assessment.grad001.lethaboletsoalo.entity.WasteCategory;

public class WasteCategoryMapper {
    public static WasteCategory toEntity(WasteCategoryDTO dto) {
        WasteCategory category = new WasteCategory();
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        return category;
    }

    public static WasteCategoryDTO toDTO(WasteCategory entity) {
        WasteCategoryDTO dto = new WasteCategoryDTO();
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        return dto;
    }
}