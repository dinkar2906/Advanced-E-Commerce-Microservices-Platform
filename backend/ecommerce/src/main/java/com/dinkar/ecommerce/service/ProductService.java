package com.dinkar.ecommerce.service;


import com.dinkar.ecommerce.dto.ProductRequest;
import com.dinkar.ecommerce.dto.ProductResponse;
import com.dinkar.ecommerce.entity.Product;
import com.dinkar.ecommerce.repository.ProductRepository;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dinkar.ecommerce.exception.ProductNotFoundException;

import java.util.List;


@Service
public class ProductService {


    private final ProductRepository productRepository;


    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }



    private ProductResponse mapToResponse(Product product){

        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .category(product.getCategory())
                .build();
    }




    public ProductResponse createProduct(ProductRequest request) {


        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .stock(request.getStock())
                .category(request.getCategory())
                .build();


        Product savedProduct = productRepository.save(product);


        return mapToResponse(savedProduct);
    }




    public List<ProductResponse> getAllProducts() {


        return productRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public Page<ProductResponse> getProductsPage(Pageable pageable) {

        return productRepository.findAll(pageable)
                .map(this::mapToResponse);
    }

    public List<ProductResponse> getProductsByCategory(String category) {

        return productRepository.findByCategory(category)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<ProductResponse> searchProducts(String name) {

        return productRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }


    public ProductResponse getProduct(Long id) {


        Product product = productRepository.findById(id)
                .orElseThrow(
                        () -> new ProductNotFoundException(
                                "Product not found with id: " + id
                        )
                );


        return mapToResponse(product);
    }




    public ProductResponse updateProduct(
            Long id,
            ProductRequest request
    ){


        Product product = productRepository.findById(id)
                .orElseThrow(
                        () -> new ProductNotFoundException(
                                "Product not found with id: " + id
                        )
                );
// prevoisly used
//        .orElseThrow(
//                () -> new RuntimeException("Product not found")
//        );

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        product.setCategory(request.getCategory());


        Product updatedProduct =
                productRepository.save(product);


        return mapToResponse(updatedProduct);
    }




    public void deleteProduct(Long id){


        Product product = productRepository.findById(id)
                .orElseThrow(
                        () -> new ProductNotFoundException(
                                "Product not found with id: " + id
                        )
                );


        productRepository.delete(product);
    }
}