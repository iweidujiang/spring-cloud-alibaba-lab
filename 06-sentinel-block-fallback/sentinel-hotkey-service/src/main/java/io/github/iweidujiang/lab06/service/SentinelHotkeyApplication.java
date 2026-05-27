package io.github.iweidujiang.lab06.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Sentinel 热点参数限流与 blockHandler/fallback 示例启动类。
 *
 * @author 苏渡苇
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SentinelHotkeyApplication {

    /**
     * 应用入口。
     *
     * @param args 启动参数
     */
    public static void main(String[] args) {
        SpringApplication.run(SentinelHotkeyApplication.class, args);
    }
}
