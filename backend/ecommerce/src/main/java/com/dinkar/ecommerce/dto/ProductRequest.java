package com.dinkar.ecommerce.dto;


import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {


    @NotBlank(message = "Product name required")
    private String name;


    private String description;


    @NotNull(message = "Price required")
    @Positive(message = "Price must be positive")
    private BigDecimal price;


    @NotNull(message = "Stock required")
    @PositiveOrZero(message = "Stock cannot be negative")
    private Integer stock;


    @NotBlank(message = "Category required")
    private String category;
}