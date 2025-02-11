package com.example.springcloud.eureka.client.product.dto;



import com.example.springcloud.eureka.client.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {
    private Long id;
    private String name;
    private String description;
    private Integer price;
    private Integer quantity;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;

    public ProductResponseDto(Product savedProduct) {
        this.id = savedProduct.getId();
        this.name =savedProduct.getName();
        this.description = savedProduct.getDescription();
        this.price = savedProduct.getPrice();
        this.quantity = savedProduct.getQuantity();
        this.createdAt = savedProduct.getCreatedAt();
        this.createdBy = savedProduct.getCreatedBy();
        this.updatedAt = savedProduct.getUpdatedAt();
        this.updatedBy = savedProduct.getUpdatedBy();
    }
}