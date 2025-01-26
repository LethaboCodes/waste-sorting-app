package com.enviro.assessment.grad001.lethaboletsoalo.config;

import com.enviro.assessment.grad001.lethaboletsoalo.entity.*;
import com.enviro.assessment.grad001.lethaboletsoalo.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // Marks this class as a source of bean definitions
public class DataInitializer {

    @Bean // Defines a bean to be managed by the Spring container
    CommandLineRunner initDatabase(
        // Dependencies injected by Spring
        WasteCategoryRepository categoryRepo, 
        DisposalGuidelineRepository guidelineRepo, 
        RecyclingTipRepository tipRepo 
    ) {
        return args -> {
            // Use the injected dependencies to initialize the database
            tipRepo.deleteAll();
            guidelineRepo.deleteAll();
            categoryRepo.deleteAll();

            WasteCategory plastic = categoryRepo.save(
                new WasteCategory("Plastic", "Various types of plastic materials")
            );
            
            WasteCategory glass = categoryRepo.save(
                new WasteCategory("Glass", "All glass container types")
            );

            guidelineRepo.save(new DisposalGuideline("Rinse containers before disposal", plastic));
            guidelineRepo.save(new DisposalGuideline("Separate by resin codes (1-7)", plastic));
            
            guidelineRepo.save(new DisposalGuideline("Wrap broken glass in paper", glass));
            guidelineRepo.save(new DisposalGuideline("Remove metal lids", glass));

            tipRepo.save(new RecyclingTip("Check local recycling codes", plastic));
            tipRepo.save(new RecyclingTip("Flatten bottles to save space", plastic));
            
            tipRepo.save(new RecyclingTip("Reuse glass jars for storage", glass));
            tipRepo.save(new RecyclingTip("Participate in bottle return programs", glass));
        };
    }
}