package io.github.iweidujiang.lab12.server;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Spring Boot Admin Server 启动类。
 *
 * @author 苏渡苇
 */
@SpringBootApplication
@EnableAdminServer
@EnableDiscoveryClient
public class BootAdminServerApplication {

    /**
     * 应用入口。
     *
     * @param args 启动参数
     */
    public static void main(String[] args) {
        SpringApplication.run(BootAdminServerApplication.class, args);
    }
}
