package com.dinkar.ecommerce.service;

import com.dinkar.ecommerce.dto.ProductRequest;
import com.dinkar.ecommerce.entity.Product;
import com.dinkar.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(ProductRequest request) {

        Product product = new Product();

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        product.setCategory(request.getCategory());

        return productRepository.save(product);
    }
}