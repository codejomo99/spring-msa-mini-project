package com.example.springcloud.eureka.client.product;

import com.example.springcloud.eureka.client.product.dto.ProductRequestDto;
import com.example.springcloud.eureka.client.product.dto.ProductResponseDto;
import com.example.springcloud.eureka.client.product.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public ProductResponseDto createProduct(ProductRequestDto requestDto, String userId) {
        Product product = Product.createProduct(requestDto,userId);
        Product savedProduct = productRepository.save(product);

        return new ProductResponseDto(savedProduct);
    }
}
