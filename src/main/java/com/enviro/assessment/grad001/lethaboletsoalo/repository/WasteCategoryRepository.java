package com.enviro.assessment.grad001.lethaboletsoalo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enviro.assessment.grad001.lethaboletsoalo.entity.WasteCategory;

public interface WasteCategoryRepository extends JpaRepository<WasteCategory, Long> {
    
    boolean existsByName(String name);
}