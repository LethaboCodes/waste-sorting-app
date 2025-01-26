package com.enviro.assessment.grad001.lethaboletsoalo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RecyclingTipDTO {
    @NotBlank(message = "Tip text is required")
    private String tip;

    @NotNull(message = "Category ID is required")
    private Long categoryId;

    public String getTip() { return tip; }
    public void setTip(String tip) { this.tip = tip; }
    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
}