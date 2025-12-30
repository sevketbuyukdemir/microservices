package com.sevketbuyukdemir.api_gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("product-service-route",
                        r -> r.path("/products", "/products/**")
                                .filters(f -> f.rewritePath("/products(?<remaining>/?.*)", "/${remaining}"))
                                .uri("lb://product-service"))
                .build();
    }
}
