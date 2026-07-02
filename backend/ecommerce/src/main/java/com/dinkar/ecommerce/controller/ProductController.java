package com.dinkar.ecommerce.controller;

import com.dinkar.ecommerce.dto.ProductRequest;
import com.dinkar.ecommerce.entity.Product;
import com.dinkar.ecommerce.service.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Product createProduct(@RequestBody ProductRequest request) {
        return productService.createProduct(request);
    }
}