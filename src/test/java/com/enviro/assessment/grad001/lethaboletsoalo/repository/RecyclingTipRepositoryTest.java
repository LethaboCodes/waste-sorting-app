package com.enviro.assessment.grad001.lethaboletsoalo.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.enviro.assessment.grad001.lethaboletsoalo.entity.RecyclingTip;
import com.enviro.assessment.grad001.lethaboletsoalo.entity.WasteCategory;

@DataJpaTest
class RecyclingTipRepositoryTest {

    @Autowired
    private RecyclingTipRepository tipRepo;
    
    @Autowired
    private WasteCategoryRepository categoryRepo;

    @Test
    void shouldSaveTipWithCategory() {
        WasteCategory category = categoryRepo.save(new WasteCategory("Paper", ""));
        RecyclingTip tip = tipRepo.save(new RecyclingTip("Shred documents", category));
        
        assertNotNull(tip.getId());
        assertEquals("Paper", tip.getCategory().getName());
    }
}