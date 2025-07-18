package com.springboot.ecom.controller;

import com.springboot.ecom.entity.Product;
import com.springboot.ecom.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for the Product module.
 * Handles incoming HTTP requests and interacts with the ProductService
 * to perform operations on Product entities.
 */
@Tag(name = "Product Module", description = "Endpoints for managing products")
@RestController
@RequestMapping("/api/products") // Base path for this module's API endpoints
public class ProductController {

    private final ProductService productService;

    /**
     * Constructs a new ProductController with the given ProductService.
     * Spring automatically injects the ProductService instance.
     *
     * @param productService The ProductService to be used by this controller.
     */
    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    /**
     * Retrieves all Product entities.
     *
     * @return A ResponseEntity containing a list of all Products and HTTP status OK.
     */
    @Operation(summary = "Retrieve all products", description = "Fetches a list of all products entities.")
    @ApiResponses(
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of products",
                    content = { @Content(mediaType = "application/json",
                        schema = @Schema(implementation = Product.class))
                    })
            )
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        final List<Product> products = this.productService.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * Retrieves a single Product entity by its ID.
     *
     * @param id The ID of the Product to retrieve.
     * @return A ResponseEntity containing the Product if found (HTTP status OK),
     * or HTTP status NOT_FOUND if not found.
     */
     @Operation(summary = "Retrieve a product by ID", description = "Fetches the details of a specific product by its ID.")
     @ApiResponses(value = {
         @ApiResponse(responseCode = "200", description = "Found the Product",
             content = { @Content(mediaType = "application/json",
                     schema = @Schema(implementation = Product.class)) }),
         @ApiResponse(responseCode = "404", description = "Product not found", content = @Content)
     })
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable final  Long id) {
        return this.productService.findById(id)
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Creates a new product entity.
     *
     * @param product The Product object to create, sent in the request body.
     * @return A ResponseEntity containing the created Product and HTTP status CREATED.
     */
     @Operation(summary = "Create a new product", description = "Creates a new product with the provided details.")
     @ApiResponses(value = {
         @ApiResponse(responseCode = "201", description = "Product created successfully",
             content = { @Content(mediaType = "application/json",
                     schema = @Schema(implementation = Product.class)) }),
         @ApiResponse(responseCode = "400", description = "Invalid product details provided", content = @Content)
     })
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody final  Product product) {
        final Product savedproduct = this.productService.save(product);
        return new ResponseEntity<>(savedproduct, HttpStatus.CREATED);
    }

    /**
     * Updates an existing Product entity.
     *
     * @param id The ID of the product to update.
     * @param product The updated Product object, sent in the request body.
     * @return A ResponseEntity containing the updated Product if found (HTTP status OK),
     * or HTTP status NOT_FOUND if the original Product is not found.
     */
     @Operation(summary = "Update an existing product", description = "Updates an existing product with the provided details.")
     @ApiResponses(value = {
         @ApiResponse(responseCode = "200", description = "Product updated successfully",
             content = { @Content(mediaType = "application/json",
                     schema = @Schema(implementation = Product.class)) }),
         @ApiResponse(responseCode = "404", description = "Product not found", content = @Content),
         @ApiResponse(responseCode = "400", description = "Invalid product details provided", content = @Content)
     })
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable final Long id, @RequestBody final Product product) {
        return this.productService.findById(id)
                .map(existingProduct -> {
                    existingProduct.setName(product.getName());
                    existingProduct.setDescription(product.getDescription());
                    // Set other fields as needed for update
                    Product updatedProduct = this.productService.save(existingProduct);
                    return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Deletes a Product entity by its ID.
     *
     * @param id The ID of the Product to delete.
     * @return A ResponseEntity with HTTP status NO_CONTENT if successful,
     * or HTTP status NOT_FOUND if the Product does not exist.
     */
     @Operation(summary = "Delete a product", description = "Deletes a product by its ID.")
     @ApiResponses(value = {
         @ApiResponse(responseCode = "204", description = "Product deleted successfully", content = @Content),
         @ApiResponse(responseCode = "404", description = "Product not found", content = @Content)
     })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable final Long id) {
        if (productService.findById(id).isPresent()) {
            this.productService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
