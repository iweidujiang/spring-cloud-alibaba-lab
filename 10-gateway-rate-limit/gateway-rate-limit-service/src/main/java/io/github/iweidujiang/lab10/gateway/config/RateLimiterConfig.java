package io.github.iweidujiang.lab10.gateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * RequestRateLimiter 限流 Key 解析配置。
 *
 * @author 苏渡苇
 */
@Configuration
public class RateLimiterConfig {

    /**
     * 按 userId 请求参数进行限流。
     *
     * @return KeyResolver
     */
    @Bean
    public KeyResolver userKeyResolver() {
        return exchange -> Mono.just(
                Objects.requireNonNull(exchange.getRequest().getQueryParams().getFirst("userId")));
    }
}
