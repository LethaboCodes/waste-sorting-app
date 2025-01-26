package com.enviro.assessment.grad001.lethaboletsoalo.service;

import com.enviro.assessment.grad001.lethaboletsoalo.dto.DisposalGuidelineDTO;
import com.enviro.assessment.grad001.lethaboletsoalo.entity.DisposalGuideline;
import com.enviro.assessment.grad001.lethaboletsoalo.entity.WasteCategory;
import com.enviro.assessment.grad001.lethaboletsoalo.exception.EntityNotFoundException;
import com.enviro.assessment.grad001.lethaboletsoalo.repository.DisposalGuidelineRepository;
import com.enviro.assessment.grad001.lethaboletsoalo.repository.WasteCategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Marks this class as a Spring service component
@Transactional // Ensures that methods in this class are executed within a transaction
public class DisposalGuidelineService {
    
    private final DisposalGuidelineRepository guidelineRepository;
    private final WasteCategoryRepository categoryRepository;

    // Constructor for dependency injection
    public DisposalGuidelineService(DisposalGuidelineRepository guidelineRepository,
                                   WasteCategoryRepository categoryRepository) {
        this.guidelineRepository = guidelineRepository;
        this.categoryRepository = categoryRepository;
    }

    // Retrieves all disposal guidelines from the repository
    public List<DisposalGuideline> getAllGuidelines() {
        return guidelineRepository.findAll();
    }

    // Retrieves a specific disposal guideline by its ID
    public DisposalGuideline getGuidelineById(Long id) {
        return guidelineRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Guideline not found"));
    }

    // Retrieves all disposal guidelines associated with a specific waste category ID
    public List<DisposalGuideline> getGuidelinesByCategory(Long categoryId) {
        return guidelineRepository.findByCategoryId(categoryId);
    }

    // Creates a new disposal guideline
    public DisposalGuideline createGuideline(DisposalGuidelineDTO guidelineDTO) {
        // Find the waste category by ID
        WasteCategory category = categoryRepository.findById(guidelineDTO.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));
        
        // Create a new DisposalGuideline object and set its properties
        DisposalGuideline guideline = new DisposalGuideline();
        guideline.setGuideline(guidelineDTO.getGuideline());
        guideline.setCategory(category);
        
        // Save the new guideline to the repository
        return guidelineRepository.save(guideline);
    }

    // Updates an existing disposal guideline
    public DisposalGuideline updateGuideline(Long id, DisposalGuidelineDTO guidelineDTO) {
        // Find the existing guideline by ID
        DisposalGuideline existing = getGuidelineById(id);
        
        // Find the waste category by ID
        WasteCategory category = categoryRepository.findById(guidelineDTO.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));
        
        // Update the properties of the existing guideline
        existing.setGuideline(guidelineDTO.getGuideline());
        existing.setCategory(category);
        
        // Save the updated guideline to the repository
        return guidelineRepository.save(existing);
    }

    // Deletes a disposal guideline by its ID
    public void deleteGuideline(Long id) {
        // Check if the guideline exists
        if (!guidelineRepository.existsById(id)) {
            throw new EntityNotFoundException("Guideline not found");
        }
        
        // Delete the guideline from the repository
        guidelineRepository.deleteById(id);
    }
}