package com.enviro.assessment.grad001.lethaboletsoalo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // Marks this class as a JPA entity
@Data // Lombok annotation to generate getters, setters, toString, equals, and hashCode methods
@NoArgsConstructor // Lombok annotation to generate a no-argument constructor
@AllArgsConstructor // Lombok annotation to generate an all-arguments constructor
public class RecyclingTip {
    @Id // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Specifies that the ID should be generated automatically
    private Long id;

    @NotBlank(message = "Tip text is required") // Ensures the tip text is not null or empty
    @Column(columnDefinition = "TEXT") // Specifies that the column type should be TEXT in the database
    private String tip;

    @ManyToOne // Defines a many-to-one relationship with WasteCategory
    @JoinColumn(name = "category_id") // Specifies the foreign key column name
    private WasteCategory category;

    // Constructor to create a RecyclingTip with tip text and category
    public RecyclingTip(String tip, WasteCategory category) {
        this.tip = tip;
        this.category = category;
    }
}