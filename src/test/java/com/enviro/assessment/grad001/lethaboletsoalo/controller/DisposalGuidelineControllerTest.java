package com.enviro.assessment.grad001.lethaboletsoalo.controller;

import com.enviro.assessment.grad001.lethaboletsoalo.entity.DisposalGuideline;
import com.enviro.assessment.grad001.lethaboletsoalo.exception.EntityNotFoundException;
import com.enviro.assessment.grad001.lethaboletsoalo.service.DisposalGuidelineService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DisposalGuidelineController.class)
class DisposalGuidelineControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("removal")
    @MockBean
    private DisposalGuidelineService guidelineService;

    @Test
    void createGuideline_Success() throws Exception {
        DisposalGuideline guideline = new DisposalGuideline();
        guideline.setId(1L);
        guideline.setGuideline("Test guideline");
        
        when(guidelineService.createGuideline(any())).thenReturn(guideline);

        mockMvc.perform(post("/api/disposal-guidelines")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"guideline\":\"Test guideline\",\"categoryId\":1}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void getGuidelinesByCategory_Success() throws Exception {
        DisposalGuideline guideline = new DisposalGuideline();
        guideline.setGuideline("Test guideline");
        
        when(guidelineService.getGuidelinesByCategory(anyLong()))
            .thenReturn(Collections.singletonList(guideline));

        mockMvc.perform(get("/api/disposal-guidelines/category/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].guideline").value("Test guideline"));
    }

    @Test
    void updateGuideline_NotFound() throws Exception {
        when(guidelineService.updateGuideline(anyLong(), any()))
            .thenThrow(new EntityNotFoundException("Guideline not found"));

        mockMvc.perform(put("/api/disposal-guidelines/999")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"guideline\":\"Updated\"}"))
                .andExpect(status().isNotFound());
    }
}