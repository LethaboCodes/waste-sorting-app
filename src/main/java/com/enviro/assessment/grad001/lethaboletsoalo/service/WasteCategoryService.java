package com.enviro.assessment.grad001.lethaboletsoalo.service;

import com.enviro.assessment.grad001.lethaboletsoalo.dto.WasteCategoryDTO;
import com.enviro.assessment.grad001.lethaboletsoalo.entity.WasteCategory;
import com.enviro.assessment.grad001.lethaboletsoalo.exception.DuplicateEntityException;
import com.enviro.assessment.grad001.lethaboletsoalo.exception.EntityNotFoundException;
import com.enviro.assessment.grad001.lethaboletsoalo.repository.WasteCategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Marks this class as a Spring service component
@Transactional // Ensures that methods in this class are executed within a transaction
public class WasteCategoryService {
    
    private final WasteCategoryRepository repository;

    // Constructor for dependency injection
    public WasteCategoryService(WasteCategoryRepository repository) {
        this.repository = repository;
    }

    // Retrieves all waste categories from the repository
    public List<WasteCategory> getAllCategories() {
        return repository.findAll();
    }

    // Retrieves a specific waste category by its ID
    public Optional<WasteCategory> getCategoryById(Long id) {
        return repository.findById(id);
    }

    // Creates a new waste category
    public WasteCategory createCategory(WasteCategoryDTO categoryDTO) {
        // Check if a category with the same name already exists
        if (repository.existsByName(categoryDTO.getName())) {
            throw new DuplicateEntityException("Category name already exists");
        }
        
        // Create a new WasteCategory object and set its properties
        WasteCategory category = new WasteCategory();
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        
        // Save the new category to the repository
        return repository.save(category);
    }

    // Updates an existing waste category
    public WasteCategory updateCategory(Long id, WasteCategoryDTO categoryDTO) {
        // Find the existing category by ID
        WasteCategory existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));
        
        // Update the properties of the existing category
        existing.setName(categoryDTO.getName());
        existing.setDescription(categoryDTO.getDescription());
        
        // Save the updated category to the repository
        return repository.save(existing);
    }

    // Deletes a waste category by its ID
    public void deleteCategory(Long id) {
        // Check if the category exists
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Category not found");
        }
        
        // Delete the category from the repository
        repository.deleteById(id);
    }
}