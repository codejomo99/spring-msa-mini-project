package com.example.springcloud.eureka.client.product;

import com.example.springcloud.eureka.client.product.dto.ProductRequestDto;
import com.example.springcloud.eureka.client.product.dto.ProductResponseDto;
import com.example.springcloud.eureka.client.product.dto.ProductSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
                                            @RequestHeader(value = "X-Role") String role) {
        if (!"MANAGER".equals(role)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied. User role is not Manager");
        }
        return productService.createProduct(requestDto, userId);
    }

    // 단일 read
    @GetMapping("/{id}")
    public ProductResponseDto getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    // 다중 read
    @GetMapping
    public Page<ProductResponseDto> getProducts(ProductSearchDto searchDto, Pageable pageable){
        return productService.getProducts(searchDto,pageable);
    }


    // update
    @PutMapping("/{id}")
    public ProductResponseDto updateProduct(@PathVariable Long id,
                                            @RequestBody ProductRequestDto orderRequestDto,
                                            @RequestHeader(value = "X-User-Id") String userId,
                                            @RequestHeader(value = "X-Role") String role) {
        return productService.updateProduct(id, orderRequestDto, userId);
    }

    // delete
    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable Long productId, @RequestParam String deletedBy) {
        productService.deleteProduct(productId, deletedBy);
    }
}
