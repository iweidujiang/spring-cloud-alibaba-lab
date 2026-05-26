package io.github.iweidujiang.lab03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Sentinel 限流实战示例启动类。
 *
 * @author 苏渡苇
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SentinelFlowApplication {

    /**
     * 应用入口。
     *
     * @param args 启动参数
     */
    public static void main(String[] args) {
        SpringApplication.run(SentinelFlowApplication.class, args);
    }
}
