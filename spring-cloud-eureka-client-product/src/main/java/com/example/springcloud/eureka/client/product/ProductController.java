package com.example.springcloud.eureka.client.product;

import com.example.springcloud.eureka.client.product.dto.ProductRequestDto;
import com.example.springcloud.eureka.client.product.dto.ProductResponseDto;
import com.example.springcloud.eureka.client.product.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // create
    @PostMapping
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto requestDto,
                                            @RequestHeader(value = "X-User-Id") String userId,
                                            @RequestHeader(value = "X-Role") String role){
        if(!"MANAGER".equals(role)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied. User role is not Manager");
        }
        return productService.createProduct(requestDto,userId);
    }

    // read

    // update

    // delete
}
