package com.enviro.assessment.grad001.lethaboletsoalo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class DisposalGuidelineDTO {
    @NotBlank(message = "Guideline text is required")
    private String guideline;

    @NotNull(message = "Category ID is required")
    private Long categoryId;

    public String getGuideline() { return guideline; }
    public void setGuideline(String guideline) { this.guideline = guideline; }
    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
}