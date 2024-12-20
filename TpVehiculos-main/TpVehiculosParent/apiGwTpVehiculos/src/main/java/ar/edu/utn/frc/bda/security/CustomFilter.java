package ar.edu.utn.frc.bda.security;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class CustomFilter implements GlobalFilter {

    private static final String ORIGIN_HEADER = "http://localhost:8080";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        exchange.getRequest().mutate().headers(httpHeaders -> {
            httpHeaders.set(HttpHeaders.ORIGIN, ORIGIN_HEADER);
        }).build();
        return chain.filter(exchange);
    }
}
