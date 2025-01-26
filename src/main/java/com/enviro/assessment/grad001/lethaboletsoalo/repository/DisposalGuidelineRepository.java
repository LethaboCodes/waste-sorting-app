package com.enviro.assessment.grad001.lethaboletsoalo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enviro.assessment.grad001.lethaboletsoalo.entity.DisposalGuideline;

public interface DisposalGuidelineRepository extends JpaRepository<DisposalGuideline, Long> {
    
    List<DisposalGuideline> findByCategoryId(Long categoryId);
}