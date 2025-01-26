package com.enviro.assessment.grad001.lethaboletsoalo.dto;

import com.enviro.assessment.grad001.lethaboletsoalo.entity.RecyclingTip;
import com.enviro.assessment.grad001.lethaboletsoalo.entity.WasteCategory;

public class RecyclingTipMapper {
    public static RecyclingTip toEntity(RecyclingTipDTO dto, WasteCategory category) {
        RecyclingTip tip = new RecyclingTip();
        tip.setTip(dto.getTip());
        tip.setCategory(category);
        return tip;
    }

    public static RecyclingTipDTO toDTO(RecyclingTip entity) {
        RecyclingTipDTO dto = new RecyclingTipDTO();
        dto.setTip(entity.getTip());
        dto.setCategoryId(entity.getCategory().getId());
        return dto;
    }
}