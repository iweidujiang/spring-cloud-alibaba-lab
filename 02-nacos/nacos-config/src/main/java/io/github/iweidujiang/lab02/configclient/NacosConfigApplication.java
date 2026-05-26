package io.github.iweidujiang.lab02.configclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Nacos 配置中心客户端启动类。
 *
 * @author 苏渡苇
 */
@SpringBootApplication
public class NacosConfigApplication {

    /**
     * 应用入口。
     *
     * @param args 启动参数
     */
    public static void main(String[] args) {
        SpringApplication.run(NacosConfigApplication.class, args);
    }
}
