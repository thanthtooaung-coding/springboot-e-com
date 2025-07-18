package com.springboot.ecom.service;

import com.springboot.ecom.entity.Product;
import com.springboot.ecom.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Service layer for managing Product entities.
 * This class contains the business logic for operations related to Product.
 * It acts as an intermediary between the Controller and Repository layers for Product data.
 */
@Service
public class ProductService {

    private final ProductRepository productRepository;

    /**
     * Constructs a new ProductService with the given ProductRepository.
     * Spring automatically injects the ProductRepository instance.
     *
     * @param productRepository The ProductRepository to be used by this service.
     */
    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Retrieves all Product entities.
     * This method fetches all Product records from the database.
     *
     * @return A list of all Product entities.
     */
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    /**
     * Retrieves a Product entity by its ID.
     * This method attempts to find a single Product based on its primary key.
     *
     * @param id The ID of the Product to retrieve.
     * @return An Optional containing the Product if found, or empty if not.
     */
    public Optional<Product> findById(final Long id) {
        return this.productRepository.findById(id);
    }

    /**
     * Saves a new Product entity or updates an existing one.
     * This method persists the Product object to the database.
     *
     * @param product The Product entity to save or update.
     * @return The saved or updated Product entity.
     */
    public Product save(final Product product) {
        return this.productRepository.save(product);
    }

    /**
     * Deletes a Product entity by its ID.
     * This method removes the Product record identified by the given ID from the database.
     *
     * @param id The ID of the Product to delete.
     */
    public void deleteById(final Long id) {
        this.productRepository.deleteById(id);
    }
}
