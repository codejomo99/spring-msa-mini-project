package com.example.springcloud.eureka.client.product;

import com.example.springcloud.eureka.client.product.dto.ProductRequestDto;
import com.example.springcloud.eureka.client.product.dto.ProductResponseDto;
import com.example.springcloud.eureka.client.product.entity.Product;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;


    @Transactional
    public ProductResponseDto createProduct(ProductRequestDto requestDto, String userId) {
        Product product = Product.createProduct(requestDto,userId);
        Product savedProduct = productRepository.save(product);

        return new ProductResponseDto(savedProduct);
    }


    @Transactional
    public ProductResponseDto updateProduct(Long productId, ProductRequestDto orderRequestDto, String userId) {
        Product product = productRepository.findById(productId)
                .filter(p-> p.getDeletedAt() == null)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found or has been deleted"));

        product.updateProduct(orderRequestDto,userId);

        return new ProductResponseDto(productRepository.save(product));
    }

    @Transactional
    public void deleteProduct(Long productId, String deletedBy) {
        Product product = productRepository.findById(productId)
                .filter(p-> p.getDeletedBy() == null)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found or has been deleted"));
        product.deleteProduct(deletedBy);
        productRepository.save(product);
    }
}
