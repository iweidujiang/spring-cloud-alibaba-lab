package io.github.iweidujiang.lab09.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * 通过 Java 代码配置网关路由（可选，默认使用 application.yml）。
 *
 * @author 苏渡苇
 */
@Configuration
@Profile("java-routes")
public class GatewayRouteConfig {

    /**
     * 使用 RouteLocator 配置路由。
     *
     * @param builder 路由构建器
     * @return 路由定位器
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/user/**")
                        .uri("lb://user-service"))
                .route(r -> r.path("/order/**")
                        .uri("lb://order-service"))
                .build();
    }
}
