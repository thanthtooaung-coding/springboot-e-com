package com.springboot.ecom.repository;

import com.springboot.ecom.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Product entity.
 * Provides standard CRUD operations and custom query capabilities for Product data.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Custom query methods can be added here if needed, e.g.:
    // Optional<Product> findByName(String name);
}
