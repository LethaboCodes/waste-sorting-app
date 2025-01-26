package com.enviro.assessment.grad001.lethaboletsoalo.controller;

import com.enviro.assessment.grad001.lethaboletsoalo.dto.DisposalGuidelineDTO;
import com.enviro.assessment.grad001.lethaboletsoalo.entity.DisposalGuideline;
import com.enviro.assessment.grad001.lethaboletsoalo.service.DisposalGuidelineService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disposal-guidelines")
public class DisposalGuidelineController {
    
    private final DisposalGuidelineService guidelineService;

    public DisposalGuidelineController(DisposalGuidelineService guidelineService) {
        this.guidelineService = guidelineService;
    }

    @GetMapping
    public ResponseEntity<List<DisposalGuideline>> getAllGuidelines() {
        List<DisposalGuideline> guidelines = guidelineService.getAllGuidelines();
        guidelines.forEach(g -> g.setCategory(null));
        return ResponseEntity.ok(guidelines);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisposalGuideline> getGuidelineById(@PathVariable Long id) {
        DisposalGuideline guideline = guidelineService.getGuidelineById(id);
        guideline.setCategory(null); 
        return ResponseEntity.ok(guideline);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<DisposalGuideline>> getGuidelinesByCategory(@PathVariable Long categoryId) {
        List<DisposalGuideline> guidelines = guidelineService.getGuidelinesByCategory(categoryId);
        guidelines.forEach(g -> g.setCategory(null)); 
        return ResponseEntity.ok(guidelines);
    }

    @PostMapping
    public ResponseEntity<DisposalGuideline> createGuideline(@Valid @RequestBody DisposalGuidelineDTO guidelineDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(guidelineService.createGuideline(guidelineDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DisposalGuideline> updateGuideline(
        @PathVariable Long id,
        @Valid @RequestBody DisposalGuidelineDTO guidelineDTO) {
        return ResponseEntity.ok(guidelineService.updateGuideline(id, guidelineDTO));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGuideline(@PathVariable Long id) {
        guidelineService.deleteGuideline(id);
    }
}