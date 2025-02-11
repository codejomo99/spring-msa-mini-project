package com.example.springcloud.eureka.client.order;


import com.example.springcloud.eureka.client.order.dto.OrderRequestDto;
import com.example.springcloud.eureka.client.order.dto.OrderResponseDto;
import com.example.springcloud.eureka.client.order.entity.Order;
import com.example.springcloud.eureka.client.order.entity.OrderStatus;
import com.example.springcloud.eureka.client.order.productclient.ProductClient;
import com.example.springcloud.eureka.client.order.productclient.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductClient productClient;

    @Transactional
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto, String userId) {
        for (Long productId : orderRequestDto.getOrderItemIds()) {
            ProductResponseDto product = productClient.getProduct(productId);
            log.info("############################ Product 수량 확인 : " + product.getQuantity());

            if (product.getQuantity() < 1) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product with ID " + productId + " is out of stock.");
            }
        }

        // Product에 있는 상품으로 부터 상품 갯수 차감하는 api
        for (Long productId : orderRequestDto.getOrderItemIds()) {
            productClient.reduceProductQuantity(productId, 1);
        }

        Order order = Order.createOrder(orderRequestDto, userId);

        return new OrderResponseDto(orderRepository.save(order));
    }

    @Transactional
    public OrderResponseDto updateOrder(Long orderId, OrderRequestDto requestDto, String userId) {
        Order order = orderRepository.findById(orderId)
                .filter(o -> o.getDeletedAt() == null)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found or has been deleted"));

        order.updateOrder(requestDto.getOrderItemIds(), userId, OrderStatus.valueOf(requestDto.getStatus()));

        return new OrderResponseDto(orderRepository.save(order));
    }

    @Transactional
    public void deleteOrder(Long orderId, String deletedBy) {
        Order order = orderRepository.findById(orderId)
                .filter(o -> o.getDeletedAt() == null)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found or has been deleted"));
        order.deleteOrder(deletedBy);
        orderRepository.save(order);
    }
}
