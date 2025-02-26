package com.isi.stock.products.controller;

import com.isi.stock.products.dto.ProductDtoRequest;
import com.isi.stock.products.dto.ProductDtoResponse;
import com.isi.stock.products.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
@Getter
@Setter
@Slf4j
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDtoResponse> saveProduct(@RequestBody @Valid ProductDtoRequest productDto) {
        log.info("Request to save product: {}", productDto);
        Optional<ProductDtoResponse> productDto1 = productService.saveProduct(productDto);
        if (productDto1.isPresent()) {
            log.info("Product saved successfully: {}", productDto1.get());
            return new ResponseEntity<>(productDto1.get(), HttpStatus.CREATED);
        } else {
            log.error("Failed to save product: {}", productDto);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{ref}")
    public ResponseEntity<ProductDtoResponse> getProduct(@PathVariable("ref") String ref) {
        log.info("Request to get product by ref: {}", ref);
        Optional<ProductDtoResponse> productDto1 = productService.getProductByRef(ref);
        if (productDto1.isPresent()) {
            log.info("Product found: {}", productDto1.get());
            return new ResponseEntity<>(productDto1.get(), HttpStatus.OK);
        } else {
            log.warn("Product not found with ref: {}", ref);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<ProductDtoResponse>> allProducts() {
        log.info("Request to get all products");
        Optional<List<ProductDtoResponse>> productDtos = productService.getAllProducts();
        if (productDtos.isPresent()) {
            log.info("Products found: {}", productDtos.get().size());
            return new ResponseEntity<>(productDtos.get(), HttpStatus.OK);
        } else {
            log.warn("No products found");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/{ref}")
    public ResponseEntity<ProductDtoResponse> updateProduct(
            @PathVariable("ref") String ref,
            @RequestBody @Valid ProductDtoRequest productDto) {
        log.info("Request to update product with ref: {}", ref);
        Optional<ProductDtoResponse> productDto1 = productService.updateProduct(ref, productDto);
        if (productDto1.isPresent()) {
            log.info("Product updated successfully: {}", productDto1.get());
            return new ResponseEntity<>(productDto1.get(), HttpStatus.OK);
        } else {
            log.warn("Product not found for update with ref: {}", ref);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{ref}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("ref") String ref) {
        log.info("Request to delete product with ref: {}", ref);
        productService.deleteProduct(ref);
        log.info("Product deleted with ref: {}", ref);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
