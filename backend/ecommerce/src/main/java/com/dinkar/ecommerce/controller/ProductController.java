package com.dinkar.ecommerce.controller;


import com.dinkar.ecommerce.dto.ProductRequest;
import com.dinkar.ecommerce.dto.ProductResponse;
import com.dinkar.ecommerce.service.ProductService;
import java.math.BigDecimal;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


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

    @GetMapping("/page")
    public ResponseEntity<Page<ProductResponse>> getProductsPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        return ResponseEntity.ok(
                productService.getProductsPage(
                        PageRequest.of(page, size)
                )
        );
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<ProductResponse>> getProductsByCategory(
            @PathVariable String category
    ) {

        return ResponseEntity.ok(
                productService.getProductsByCategory(category)
        );
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponse>> searchProducts(
            @RequestParam String name
    ) {

        return ResponseEntity.ok(
                productService.searchProducts(name)
        );
    }


    @GetMapping("/filter")
    public ResponseEntity<List<ProductResponse>> filterProducts(

            @RequestParam(required = false) String category,

            @RequestParam(required = false) BigDecimal minPrice,

            @RequestParam(required = false) BigDecimal maxPrice,

            @RequestParam(defaultValue = "id") String sort,

            @RequestParam(defaultValue = "asc") String direction
    ) {

        return ResponseEntity.ok(
                productService.filterProducts(
                        category,
                        minPrice,
                        maxPrice,
                        sort,
                        direction
                )
        );
    }


}