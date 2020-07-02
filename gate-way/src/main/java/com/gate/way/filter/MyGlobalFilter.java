package com.gate.way.filter;

/**
 * @author 180465
 * @date 2020/7/2 15:40
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 各个实现类的顺序如下（ 数值越小，优先级越高，括号中为源码中的值 ）：
 *
 * AdaptCachedBodyGlobalFilter ：-2147482648 （ Ordered.HIGHEST_PRECEDENCE + 1000 ）
 * ForwardPathFilter：0
 * ForwardRoutingFilter：2147483647 （ Ordered.LOWEST_PRECEDENCE ）
 * GatewayMetricsFilter：0
 * LoadBalancerClientFilter：10100
 * NettyRoutingFilter：2147483647 （ Ordered.LOWEST_PRECEDENCE ）
 * NettyWriteResponseFilter：-1
 * RouteToRequestUrlFilter：10000
 * WebClientHttpRoutingFilter：2147483647 （ Ordered.LOWEST_PRECEDENCE ）
 * WebClientWriteResponseFilter：-1
 * WebsocketRoutingFilter：2147483646 （ Ordered.LOWEST_PRECEDENCE - 1 ）
 *
 */
@Component
@Slf4j
public class MyGlobalFilter implements GlobalFilter, Ordered {
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getQueryParams().getFirst("token");
        if (token == null || token.equals("")) {
            log.info( "token is empty..." );
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    public int getOrder() {
        return 0;
    }
}
