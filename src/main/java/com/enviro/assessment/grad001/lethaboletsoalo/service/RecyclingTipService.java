package com.enviro.assessment.grad001.lethaboletsoalo.service;

import com.enviro.assessment.grad001.lethaboletsoalo.dto.RecyclingTipDTO;
import com.enviro.assessment.grad001.lethaboletsoalo.entity.RecyclingTip;
import com.enviro.assessment.grad001.lethaboletsoalo.entity.WasteCategory;
import com.enviro.assessment.grad001.lethaboletsoalo.exception.EntityNotFoundException;
import com.enviro.assessment.grad001.lethaboletsoalo.repository.RecyclingTipRepository;
import com.enviro.assessment.grad001.lethaboletsoalo.repository.WasteCategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Marks this class as a Spring service component
@Transactional // Ensures that methods in this class are executed within a transaction
public class RecyclingTipService {
    
    private final RecyclingTipRepository tipRepository;
    private final WasteCategoryRepository categoryRepository;

    // Constructor for dependency injection
    public RecyclingTipService(RecyclingTipRepository tipRepository,
                              WasteCategoryRepository categoryRepository) {
        this.tipRepository = tipRepository;
        this.categoryRepository = categoryRepository;
    }

    // Retrieves all recycling tips from the repository
    public List<RecyclingTip> getAllTips() {
        return tipRepository.findAll();
    }

    // Retrieves a specific recycling tip by its ID
    public RecyclingTip getTipById(Long id) {
        return tipRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tip not found"));
    }

    // Retrieves all recycling tips associated with a specific waste category ID
    public List<RecyclingTip> getTipsByCategory(Long categoryId) {
        return tipRepository.findByCategoryId(categoryId);
    }

    // Creates a new recycling tip
    public RecyclingTip createTip(RecyclingTipDTO tipDTO) {
        // Find the waste category by ID
        WasteCategory category = categoryRepository.findById(tipDTO.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));
        
        // Create a new RecyclingTip object and set its properties
        RecyclingTip tip = new RecyclingTip();
        tip.setTip(tipDTO.getTip());
        tip.setCategory(category);
        
        // Save the new tip to the repository
        return tipRepository.save(tip);
    }

    // Updates an existing recycling tip
    public RecyclingTip updateTip(Long id, RecyclingTipDTO tipDTO) {
        // Find the existing tip by ID
        RecyclingTip existing = getTipById(id);
        
        // Find the waste category by ID
        WasteCategory category = categoryRepository.findById(tipDTO.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));
        
        // Update the properties of the existing tip
        existing.setTip(tipDTO.getTip());
        existing.setCategory(category);
        
        // Save the updated tip to the repository
        return tipRepository.save(existing);
    }

    // Deletes a recycling tip by its ID
    public void deleteTip(Long id) {
        // Check if the tip exists
        if (!tipRepository.existsById(id)) {
            throw new EntityNotFoundException("Tip not found");
        }
        
        // Delete the tip from the repository
        tipRepository.deleteById(id);
    }
}