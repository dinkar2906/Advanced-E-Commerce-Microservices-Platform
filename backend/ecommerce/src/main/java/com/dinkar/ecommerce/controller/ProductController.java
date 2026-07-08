package com.dinkar.ecommerce.controller;


import com.dinkar.ecommerce.dto.ProductRequest;
import com.dinkar.ecommerce.entity.Product;
import com.dinkar.ecommerce.service.ProductService;

import jakarta.validation.Valid;

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
    public Product createProduct(
            @Valid @RequestBody ProductRequest request
    ){

        return productService.createProduct(request);
    }



    @GetMapping
    public List<Product> getProducts(){

        return productService.getAllProducts();
    }



    @GetMapping("/{id}")
    public Product getProduct(
            @PathVariable Long id
    ){

        return productService.getProduct(id);
    }



    @PutMapping("/{id}")
    public Product updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequest request
    ){

        return productService.updateProduct(id, request);
    }



    @DeleteMapping("/{id}")
    public String deleteProduct(
            @PathVariable Long id
    ){

        productService.deleteProduct(id);

        return "Product deleted successfully";
    }
}