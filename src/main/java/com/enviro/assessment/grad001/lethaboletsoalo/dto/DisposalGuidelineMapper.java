package com.enviro.assessment.grad001.lethaboletsoalo.dto;

import com.enviro.assessment.grad001.lethaboletsoalo.entity.DisposalGuideline;
import com.enviro.assessment.grad001.lethaboletsoalo.entity.WasteCategory;

public class DisposalGuidelineMapper {
    public static DisposalGuideline toEntity(DisposalGuidelineDTO dto, WasteCategory category) {
        DisposalGuideline guideline = new DisposalGuideline();
        guideline.setGuideline(dto.getGuideline());
        guideline.setCategory(category);
        return guideline;
    }

    public static DisposalGuidelineDTO toDTO(DisposalGuideline entity) {
        DisposalGuidelineDTO dto = new DisposalGuidelineDTO();
        dto.setGuideline(entity.getGuideline());
        dto.setCategoryId(entity.getCategory().getId());
        return dto;
    }
}