package com.springboot.ecom.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Represents the Product entity in the database.
 * This class is mapped to a database table and defines the schema for Product data.
 */
@Entity
@Data // Lombok: Generates getters, setters, toString, equals, and hashCode methods
@NoArgsConstructor // Lombok: Generates a no-argument constructor
@AllArgsConstructor // Lombok: Generates a constructor with all fields
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String imageUrl;
    private BigDecimal price;

    // Additional fields can be added here based on specific requirements

    /**
     * Constructor for creating a new Product without an ID (for persistence).
     *
     * @param name The name of the Product.
     * @param description A brief description of the Product.
     */
    public Product(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
