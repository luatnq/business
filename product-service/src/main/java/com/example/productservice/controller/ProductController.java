package com.example.productservice.controller;

import com.example.model.product.ProductDTO;
import com.example.productservice.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/products")
@Slf4j
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping(produces = "application/json")
    public ResponseEntity<?> createProduct(@RequestBody ProductDTO productDTO) {
        log.info("Create product: {}", productDTO);
        ProductDTO createdProduct = productService.createProduct(productDTO);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @PutMapping(produces = "application/json")
    public ResponseEntity<?> updateProduct(@RequestBody ProductDTO productDTO) {
        log.info("Update product: {}", productDTO);
        ProductDTO updatedProduct = productService.editProduct(productDTO);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> deleteProduct(@PathVariable long id) {
        log.info("Delete product with id = {}", id);
        productService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> getProductById(@PathVariable long id) {
        log.info("Search product by id = {}", id);
        ProductDTO product = productService.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getProductByCategoryId(@RequestParam long categoryId) {
        log.info("Search product by categoryId = {}", categoryId);
        List<ProductDTO> listProduct = productService.findProductByCategoryId(categoryId);
        return new ResponseEntity<>(listProduct, HttpStatus.OK);
    }

}
