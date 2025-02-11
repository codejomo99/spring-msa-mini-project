package com.example.springcloud.eureka.client.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SpringCloudEurekaClientOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudEurekaClientOrderApplication.class, args);
    }

}
