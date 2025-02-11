package com.example.springcloud.eureka.client.order;

import com.example.springcloud.eureka.client.order.dto.OrderRequestDto;
import com.example.springcloud.eureka.client.order.dto.OrderResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    // create
    @PostMapping
    public OrderResponseDto createOrder(@RequestBody OrderRequestDto orderRequestDto,
                                        @RequestHeader(value = "X-User-Id") String userId,
                                        @RequestHeader(value = "X-Role") String role){

        return orderService.createOrder(orderRequestDto,userId);
    }

    // read (단일)
    @GetMapping("/{orderId}")
    public OrderResponseDto getOrderById(@PathVariable Long orderId) {
        return orderService.getOrderById(orderId);
    }

    // read (전체)

    // update
    @PutMapping("/{orderId}")
    public OrderResponseDto updateOrder(@PathVariable Long orderId,
                                        @RequestBody OrderRequestDto orderRequestDto,
                                        @RequestHeader(value = "X-User-Id", required = true) String userId,
                                        @RequestHeader(value = "X-Role", required = true) String role) {
        return orderService.updateOrder(orderId, orderRequestDto, userId);
    }

    // delete
    @DeleteMapping("/{orderId}")
    public void deleteOrder(@PathVariable Long orderId, @RequestParam String deletedBy) {
        orderService.deleteOrder(orderId, deletedBy);
    }

}
