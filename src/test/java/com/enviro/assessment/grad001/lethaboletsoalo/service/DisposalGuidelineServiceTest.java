package com.enviro.assessment.grad001.lethaboletsoalo.service;

import com.enviro.assessment.grad001.lethaboletsoalo.dto.DisposalGuidelineDTO;
import com.enviro.assessment.grad001.lethaboletsoalo.entity.DisposalGuideline;
import com.enviro.assessment.grad001.lethaboletsoalo.entity.WasteCategory;
import com.enviro.assessment.grad001.lethaboletsoalo.exception.EntityNotFoundException;
import com.enviro.assessment.grad001.lethaboletsoalo.repository.DisposalGuidelineRepository;
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
class DisposalGuidelineServiceTest {

    @Mock
    private DisposalGuidelineRepository guidelineRepository;

    @Mock
    private WasteCategoryRepository categoryRepository;

    @InjectMocks
    private DisposalGuidelineService service;

    @Test
    void shouldCreateGuideline() {
        DisposalGuidelineDTO dto = new DisposalGuidelineDTO();
        dto.setGuideline("Test");
        dto.setCategoryId(1L);
        
        when(categoryRepository.findById(1L))
            .thenReturn(Optional.of(new WasteCategory(1L, "Plastic", "")));
        when(guidelineRepository.save(any()))
            .thenReturn(new DisposalGuideline(1L, "Test", null));
        
        DisposalGuideline result = service.createGuideline(dto);
        assertEquals("Test", result.getGuideline());
    }

    @Test
    void shouldThrowWhenInvalidCategory() {
        DisposalGuidelineDTO dto = new DisposalGuidelineDTO();
        dto.setCategoryId(999L);
        
        when(categoryRepository.findById(999L)).thenReturn(Optional.empty());
        
        assertThrows(EntityNotFoundException.class, () -> {
            service.createGuideline(dto);
        });
    }

    @Test
    void shouldDeleteGuideline() {
        when(guidelineRepository.existsById(1L)).thenReturn(true);
        service.deleteGuideline(1L);
        verify(guidelineRepository).deleteById(1L);
    }

    @Test
    void shouldThrowWhenDeletingNonExisting() {
        when(guidelineRepository.existsById(1L)).thenReturn(false);
        assertThrows(EntityNotFoundException.class, () -> {
            service.deleteGuideline(1L);
        });
    }
}