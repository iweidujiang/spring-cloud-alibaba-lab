package io.github.iweidujiang.lab02.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Nacos 服务消费者启动类。
 *
 * @author 苏渡苇
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NacosConsumerApplication {

    /**
     * 应用入口。
     *
     * @param args 启动参数
     */
    public static void main(String[] args) {
        SpringApplication.run(NacosConsumerApplication.class, args);
    }
}
