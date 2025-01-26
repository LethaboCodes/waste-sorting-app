package com.enviro.assessment.grad001.lethaboletsoalo.controller;

import com.enviro.assessment.grad001.lethaboletsoalo.entity.WasteCategory;
import com.enviro.assessment.grad001.lethaboletsoalo.exception.DuplicateEntityException;
import com.enviro.assessment.grad001.lethaboletsoalo.service.WasteCategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WasteCategoryController.class)
class WasteCategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("removal")
    @MockBean
    private WasteCategoryService categoryService;

    @Test
    void getAllCategories_Success() throws Exception {
        WasteCategory category = new WasteCategory();
        category.setName("Plastic");
        when(categoryService.getAllCategories()).thenReturn(Collections.singletonList(category));

        mockMvc.perform(get("/api/waste-categories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Plastic"));
    }

    @Test
    void getCategoryById_Success() throws Exception {
        WasteCategory category = new WasteCategory();
        category.setId(1L);
        category.setName("Plastic");
        when(categoryService.getCategoryById(1L)).thenReturn(Optional.of(category));

        mockMvc.perform(get("/api/waste-categories/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Plastic"));
    }

    @Test
    void getCategoryById_NotFound() throws Exception {
        when(categoryService.getCategoryById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/waste-categories/1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Category not found"));
    }

    @Test
    void createCategory_Success() throws Exception {
        WasteCategory category = new WasteCategory();
        category.setId(1L);
        category.setName("Plastic");
        when(categoryService.createCategory(any())).thenReturn(category);

        mockMvc.perform(post("/api/waste-categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Plastic\",\"description\":\"Plastic materials\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void createCategory_DuplicateName() throws Exception {
        when(categoryService.createCategory(any()))
            .thenThrow(new DuplicateEntityException("Category name exists"));

        mockMvc.perform(post("/api/waste-categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Plastic\"}"))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value("Category name exists"));
    }

    @Test
    void updateCategory_Success() throws Exception {
        WasteCategory category = new WasteCategory();
        category.setId(1L);
        category.setName("Updated");
        when(categoryService.updateCategory(eq(1L), any())).thenReturn(category);

        mockMvc.perform(put("/api/waste-categories/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Updated\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated"));
    }

    @Test
    void deleteCategory_Success() throws Exception {
        mockMvc.perform(delete("/api/waste-categories/1"))
                .andExpect(status().isNoContent());
        
        verify(categoryService).deleteCategory(1L);
    }
}