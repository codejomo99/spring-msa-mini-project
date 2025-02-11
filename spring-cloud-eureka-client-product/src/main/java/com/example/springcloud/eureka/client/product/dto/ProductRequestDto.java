package com.example.springcloud.eureka.client.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDto {
    private String name;
    private String description;
    private Integer price;
    private Integer quantity;
}