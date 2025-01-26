package com.enviro.assessment.grad001.lethaboletsoalo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity // Marks this class as a JPA entity
@Data // Lombok annotation to generate getters, setters, toString, equals, and hashCode methods
@NoArgsConstructor // Lombok annotation to generate a no-argument constructor
@AllArgsConstructor // Lombok annotation to generate an all-arguments constructor
public class WasteCategory {
    @Id // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Specifies that the ID should be generated automatically
    private Long id;

    @NotBlank(message = "Category name is required") // Ensures the name is not null or empty
    @Size(max = 100, message = "Category name must be less than 100 characters") // Limits the length of the name
    @Column(unique = true) // Ensures the name is unique in the database
    private String name;

    @Size(max = 500, message = "Description must be less than 500 characters") // Limits the length of the description
    private String description;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true) // Defines a one-to-many relationship with DisposalGuideline
    @JsonIgnore // Prevents this field from being serialized to JSON to avoid infinite recursion and reduce payload size
    private List<DisposalGuideline> disposalGuidelines = new ArrayList<>();

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true) // Defines a one-to-many relationship with RecyclingTip
    @JsonIgnore // Prevents this field from being serialized to JSON to avoid infinite recursion and reduce payload size
    private List<RecyclingTip> recyclingTips = new ArrayList<>();

    // Constructor to create a WasteCategory with name and description
    public WasteCategory(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public WasteCategory(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}