package io.github.iweidujiang.lab12.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Admin 监控示例服务启动类。
 *
 * @author 苏渡苇
 */
@SpringBootApplication
@EnableDiscoveryClient
public class BootAdminDemoApplication {

    /**
     * 应用入口。
     *
     * @param args 启动参数
     */
    public static void main(String[] args) {
        SpringApplication.run(BootAdminDemoApplication.class, args);
    }
}
