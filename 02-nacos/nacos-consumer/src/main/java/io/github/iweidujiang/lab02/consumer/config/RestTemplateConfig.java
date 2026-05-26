package io.github.iweidujiang.lab02.consumer.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplate 配置，启用 LoadBalancer 负载均衡。
 *
 * @author 苏渡苇
 */
@Configuration
public class RestTemplateConfig {

    /**
     * 创建支持服务名调用的 RestTemplate。
     *
     * @return RestTemplate 实例
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
