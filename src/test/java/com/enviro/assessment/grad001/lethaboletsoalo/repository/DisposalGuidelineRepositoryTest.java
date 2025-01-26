package com.enviro.assessment.grad001.lethaboletsoalo.repository;

import com.enviro.assessment.grad001.lethaboletsoalo.entity.DisposalGuideline;
import com.enviro.assessment.grad001.lethaboletsoalo.entity.WasteCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DisposalGuidelineRepositoryTest {

    @Autowired
    private DisposalGuidelineRepository guidelineRepository;
    
    @Autowired
    private WasteCategoryRepository categoryRepository;
    
    private WasteCategory category;

    @BeforeEach
    void setup() {
        category = categoryRepository.save(new WasteCategory("Plastic", ""));
    }

    @Test
    void shouldFindByCategoryId() {
        guidelineRepository.save(new DisposalGuideline("Rinse containers", category));
        
        List<DisposalGuideline> guidelines = guidelineRepository.findByCategoryId(category.getId());
        assertEquals(1, guidelines.size());
        assertEquals("Rinse containers", guidelines.get(0).getGuideline());
    }

}