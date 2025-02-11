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

    // read

    // update

    // delete


}
