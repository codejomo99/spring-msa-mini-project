package com.mini.springcloud.eureka.client.gateway;

import java.util.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;



/**
 * CustomPostFilter 클래스는 Spring Cloud Gateway에서 글로벌하게 적용되는 Post Filter입니다.
 * 요청이 처리된 후(response가 반환되기 전)에 실행되며, 응답의 상태 코드를 로깅하는 역할을 합니다.
 */
@Slf4j
@Component
public class CustomPostFilter implements GlobalFilter, Ordered {
    private static final Logger logger = Logger.getLogger(CustomPostFilter.class.getName());

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, org.springframework.cloud.gateway.filter.GatewayFilterChain chain){
        return chain.filter(exchange).then(Mono.fromRunnable(()->{
            ServerHttpResponse response = exchange.getResponse();
            logger.info("Post Filter: Response status code is" + response.getStatusCode());
        }));
    }

    // LOWEST_PRECEDENCE를 반환하여 가장 마지막에 실행되도록 설정합니다.
    @Override
    public int getOrder(){
        return Ordered.LOWEST_PRECEDENCE;
    }

}
