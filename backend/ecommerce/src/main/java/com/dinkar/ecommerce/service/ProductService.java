package com.dinkar.ecommerce.service;

import com.dinkar.ecommerce.dto.ProductRequest;
import com.dinkar.ecommerce.entity.Product;
import com.dinkar.ecommerce.repository.ProductRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {


    private final ProductRepository productRepository;


    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


//    public Product createProduct(ProductRequest request) {
//
//        Product product = new Product();
//
//        product.setName(request.getName());
//        product.setDescription(request.getDescription());
//        product.setPrice(request.getPrice());
//        product.setStock(request.getStock());
//        product.setCategory(request.getCategory());
//
//        return productRepository.save(product);
//    }


    public Product createProduct(ProductRequest request) {

        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .stock(request.getStock())
                .category(request.getCategory())
                .build();

        return productRepository.save(product);
    }


    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }


    public Product getProduct(Long id) {

        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }


    public Product updateProduct(Long id, ProductRequest request) {

        Product product = getProduct(id);

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        product.setCategory(request.getCategory());

        return productRepository.save(product);
    }


    public void deleteProduct(Long id) {

        Product product = getProduct(id);

        productRepository.delete(product);
    }
}