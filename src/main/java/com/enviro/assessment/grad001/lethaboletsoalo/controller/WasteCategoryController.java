package com.enviro.assessment.grad001.lethaboletsoalo.controller;

import com.enviro.assessment.grad001.lethaboletsoalo.dto.WasteCategoryDTO;
import com.enviro.assessment.grad001.lethaboletsoalo.entity.WasteCategory;
import com.enviro.assessment.grad001.lethaboletsoalo.exception.EntityNotFoundException;
import com.enviro.assessment.grad001.lethaboletsoalo.service.WasteCategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/waste-categories")
public class WasteCategoryController {
    
    private final WasteCategoryService categoryService;

    public WasteCategoryController(WasteCategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<WasteCategory>> getAllCategories() {
        List<WasteCategory> categories = categoryService.getAllCategories();
        categories.forEach(category -> {
            category.setDisposalGuidelines(null);
            category.setRecyclingTips(null);
        });
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WasteCategory> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found")));
    }

    @PostMapping
    public ResponseEntity<WasteCategory> createCategory(@Valid @RequestBody WasteCategoryDTO categoryDTO) {
        WasteCategory created = categoryService.createCategory(categoryDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WasteCategory> updateCategory(
            @PathVariable Long id, 
            @Valid @RequestBody WasteCategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.updateCategory(id, categoryDTO));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }
}