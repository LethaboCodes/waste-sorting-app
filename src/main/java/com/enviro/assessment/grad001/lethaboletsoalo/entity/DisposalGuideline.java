package com.enviro.assessment.grad001.lethaboletsoalo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // Marks this class as a JPA entity
@Data // Lombok annotation to generate getters, setters, toString, equals, and hashCode methods
@NoArgsConstructor // Lombok annotation to generate a no-argument constructor
@AllArgsConstructor // Lombok annotation to generate an all-arguments constructor
public class DisposalGuideline {
    @Id // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Specifies that the ID should be generated automatically
    private Long id;

    @NotBlank(message = "Guideline text is required") // Ensures the guideline text is not null or empty
    @Column(columnDefinition = "TEXT") // Specifies that the column type should be TEXT in the database
    private String guideline;

    @ManyToOne(fetch = FetchType.LAZY) // Defines a many-to-one relationship with WasteCategory, with lazy loading
    @JoinColumn(name = "category_id", nullable = false) // Specifies the foreign key column name and makes it non-nullable
    @JsonIgnore // Prevents this field from being serialized to JSON to avoid infinite recursion and reduce payload size
    private WasteCategory category;

    // Constructor to create a DisposalGuideline with guideline text and category
    public DisposalGuideline(String guideline, WasteCategory category) {
        this.guideline = guideline;
        this.category = category;
    }
}