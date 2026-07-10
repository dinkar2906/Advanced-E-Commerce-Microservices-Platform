package com.dinkar.ecommerce.controller;


import com.dinkar.ecommerce.dto.ProductRequest;
import com.dinkar.ecommerce.dto.ProductResponse;
import com.dinkar.ecommerce.service.ProductService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/products")
public class ProductController {


    private final ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }



    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(
            @Valid @RequestBody ProductRequest request
    ){

        ProductResponse response =
                productService.createProduct(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }




    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProducts(){

        return ResponseEntity.ok(
                productService.getAllProducts()
        );
    }




    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(
            @PathVariable Long id
    ){

        return ResponseEntity.ok(
                productService.getProduct(id)
        );
    }




    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequest request
    ){

        return ResponseEntity.ok(
                productService.updateProduct(id, request)
        );
    }




    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(
            @PathVariable Long id
    ){

        productService.deleteProduct(id);

        return ResponseEntity.ok(
                "Product deleted successfully"
        );
    }
}