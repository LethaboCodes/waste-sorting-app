package com.enviro.assessment.grad001.lethaboletsoalo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enviro.assessment.grad001.lethaboletsoalo.entity.RecyclingTip;

public interface RecyclingTipRepository extends JpaRepository<RecyclingTip, Long> {
    
    List<RecyclingTip> findByCategoryId(Long categoryId);
}