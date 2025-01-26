package com.enviro.assessment.grad001.lethaboletsoalo.service;

import com.enviro.assessment.grad001.lethaboletsoalo.dto.WasteCategoryDTO;
import com.enviro.assessment.grad001.lethaboletsoalo.entity.WasteCategory;
import com.enviro.assessment.grad001.lethaboletsoalo.exception.DuplicateEntityException;
import com.enviro.assessment.grad001.lethaboletsoalo.exception.EntityNotFoundException;
import com.enviro.assessment.grad001.lethaboletsoalo.repository.WasteCategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WasteCategoryServiceTest {

    @Mock
    private WasteCategoryRepository repository;

    @InjectMocks
    private WasteCategoryService service;

    @Test
    void shouldCreateCategory() {
        WasteCategoryDTO dto = new WasteCategoryDTO();
        dto.setName("Plastic");
        
        when(repository.existsByName("Plastic")).thenReturn(false);
        when(repository.save(any())).thenReturn(new WasteCategory(1L, "Plastic", ""));
        
        WasteCategory result = service.createCategory(dto);
        assertEquals("Plastic", result.getName());
        verify(repository).save(any());
    }

    @Test
    void shouldThrowWhenDuplicateName() {
        WasteCategoryDTO dto = new WasteCategoryDTO();
        dto.setName("Plastic");
        
        when(repository.existsByName("Plastic")).thenReturn(true);
        
        assertThrows(DuplicateEntityException.class, () -> {
            service.createCategory(dto);
        });
    }

    @Test
    void shouldUpdateCategory() {
        WasteCategory existing = new WasteCategory(1L, "Old", "");
        WasteCategoryDTO dto = new WasteCategoryDTO();
        dto.setName("New");
        
        when(repository.findById(1L)).thenReturn(Optional.of(existing));
        when(repository.save(any())).thenReturn(new WasteCategory(1L, "New", ""));
        
        WasteCategory updated = service.updateCategory(1L, dto);
        assertEquals("New", updated.getName());
    }

    @Test
    void shouldThrowWhenUpdatingNonExisting() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        
        assertThrows(EntityNotFoundException.class, () -> {
            service.updateCategory(1L, new WasteCategoryDTO());
        });
    }
}