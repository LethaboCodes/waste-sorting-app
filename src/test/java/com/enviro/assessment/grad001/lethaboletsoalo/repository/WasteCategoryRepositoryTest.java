package com.enviro.assessment.grad001.lethaboletsoalo.repository;

import com.enviro.assessment.grad001.lethaboletsoalo.entity.WasteCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class WasteCategoryRepositoryTest {

    @Autowired
    private WasteCategoryRepository repository;

    @Test
    void shouldSaveAndRetrieveCategory() {
        WasteCategory category = new WasteCategory();
        category.setName("Plastic");
        category.setDescription("Plastic materials");
        
        WasteCategory saved = repository.save(category);
        WasteCategory found = repository.findById(saved.getId()).get();
        
        assertEquals(saved.getName(), found.getName());
    }
    
    @Test
    void shouldReturnTrueWhenNameExists() {
        repository.save(new WasteCategory("Plastic", ""));
        assertTrue(repository.existsByName("Plastic"));
    }

    @Test
    void shouldThrowWhenDuplicateName() {
        repository.save(new WasteCategory("Plastic", ""));
        assertThrows(DataIntegrityViolationException.class, () -> {
            repository.save(new WasteCategory("Plastic", "Different description"));
        });
    }
}