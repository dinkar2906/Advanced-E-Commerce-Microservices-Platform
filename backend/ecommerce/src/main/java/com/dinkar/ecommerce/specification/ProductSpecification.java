package com.dinkar.ecommerce.specification;

import com.dinkar.ecommerce.entity.Product;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ProductSpecification {

    public static Specification<Product> filterProducts(
            String category,
            BigDecimal minPrice,
            BigDecimal maxPrice
    ) {

        return (root, query, cb) -> {

            var predicate = cb.conjunction();

            if (category != null && !category.isBlank()) {
                predicate = cb.and(
                        predicate,
                        cb.equal(root.get("category"), category)
                );
            }

            if (minPrice != null) {
                predicate = cb.and(
                        predicate,
                        cb.greaterThanOrEqualTo(root.get("price"), minPrice)
                );
            }

            if (maxPrice != null) {
                predicate = cb.and(
                        predicate,
                        cb.lessThanOrEqualTo(root.get("price"), maxPrice)
                );
            }

            return predicate;
        };
    }
}