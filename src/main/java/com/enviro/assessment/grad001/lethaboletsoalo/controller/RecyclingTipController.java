package com.enviro.assessment.grad001.lethaboletsoalo.controller;

import com.enviro.assessment.grad001.lethaboletsoalo.dto.RecyclingTipDTO;
import com.enviro.assessment.grad001.lethaboletsoalo.entity.RecyclingTip;
import com.enviro.assessment.grad001.lethaboletsoalo.service.RecyclingTipService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recycling-tips")
public class RecyclingTipController {
    
    private final RecyclingTipService tipService;

    public RecyclingTipController(RecyclingTipService tipService) {
        this.tipService = tipService;
    }

    @GetMapping
    public ResponseEntity<List<RecyclingTip>> getAllTips() {
        return ResponseEntity.ok(tipService.getAllTips());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecyclingTip> getTipById(@PathVariable Long id) {
        return ResponseEntity.ok(tipService.getTipById(id));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<RecyclingTip>> getTipsByCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(tipService.getTipsByCategory(categoryId));
    }

    @PostMapping
    public ResponseEntity<RecyclingTip> createTip(@Valid @RequestBody RecyclingTipDTO tipDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(tipService.createTip(tipDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecyclingTip> updateTip(
            @PathVariable Long id,
            @Valid @RequestBody RecyclingTipDTO tipDTO) {
        return ResponseEntity.ok(tipService.updateTip(id, tipDTO));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTip(@PathVariable Long id) {
        tipService.deleteTip(id);
    }
}