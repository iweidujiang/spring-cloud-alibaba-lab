package io.github.iweidujiang.lab07.consumer.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenFeign 全局配置。
 *
 * @author 苏渡苇
 */
@Configuration
public class FeignConfig {

    /**
     * 开启 Feign 详细日志。
     *
     * @return Feign 日志级别
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
