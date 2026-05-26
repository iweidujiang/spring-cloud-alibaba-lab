package io.github.iweidujiang.lab02.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Nacos 服务提供者启动类。
 *
 * @author 苏渡苇
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NacosProviderApplication {

    /**
     * 应用入口。
     *
     * @param args 启动参数
     */
    public static void main(String[] args) {
        SpringApplication.run(NacosProviderApplication.class, args);
    }
}
