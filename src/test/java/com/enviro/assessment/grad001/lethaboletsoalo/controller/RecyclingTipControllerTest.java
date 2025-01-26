package com.enviro.assessment.grad001.lethaboletsoalo.controller;

import com.enviro.assessment.grad001.lethaboletsoalo.exception.EntityNotFoundException;
import com.enviro.assessment.grad001.lethaboletsoalo.service.RecyclingTipService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RecyclingTipController.class)
class RecyclingTipControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("removal")
    @MockBean
    private RecyclingTipService tipService;

    @Test
    void createTip_InvalidInput() throws Exception {
        mockMvc.perform(post("/api/recycling-tips")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"tip\":\"\"}"))  // Empty tip
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.details[0]").value("tip: Tip text is required"));
    }

    @Test
    void deleteTip_Success() throws Exception {
        mockMvc.perform(delete("/api/recycling-tips/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void getTipById_NotFound() throws Exception {
        when(tipService.getTipById(anyLong()))
            .thenThrow(new EntityNotFoundException("Tip not found"));

        mockMvc.perform(get("/api/recycling-tips/999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Tip not found"));
    }

    @Test
    void getTipsByCategory_Empty() throws Exception {
        when(tipService.getTipsByCategory(anyLong())).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/recycling-tips/category/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }
}