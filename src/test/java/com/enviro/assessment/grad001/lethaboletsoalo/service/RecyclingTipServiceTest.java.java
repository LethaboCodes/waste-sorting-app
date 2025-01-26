package com.enviro.assessment.grad001.lethaboletsoalo.service;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.enviro.assessment.grad001.lethaboletsoalo.dto.RecyclingTipDTO;
import com.enviro.assessment.grad001.lethaboletsoalo.entity.RecyclingTip;
import com.enviro.assessment.grad001.lethaboletsoalo.entity.WasteCategory;
import com.enviro.assessment.grad001.lethaboletsoalo.repository.RecyclingTipRepository;
import com.enviro.assessment.grad001.lethaboletsoalo.repository.WasteCategoryRepository;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class RecyclingTipServiceTest {

    @Mock
    private RecyclingTipRepository tipRepository;
    
    @Mock
    private WasteCategoryRepository categoryRepository;
    
    @InjectMocks
    private RecyclingTipService service;

    @Test
    void createTip_Success() {
        RecyclingTipDTO dto = new RecyclingTipDTO();
        dto.setTip("Reuse containers");
        dto.setCategoryId(1L);

        WasteCategory category = new WasteCategory(1L, "Plastic", "");
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(tipRepository.save(any()))
            .thenReturn(new RecyclingTip(1L, "Reuse containers", category));

        RecyclingTip result = service.createTip(dto);
        assertEquals("Reuse containers", result.getTip());
        assertEquals(1L, result.getId());
    }
}