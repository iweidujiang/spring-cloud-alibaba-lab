package io.github.iweidujiang.lab15.ware;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 库存服务启动类（Seata RM）。
 *
 * @author 苏渡苇
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("io.github.iweidujiang.lab15.ware.mapper")
public class SeataAtWareApplication {

    /**
     * 应用入口。
     *
     * @param args 启动参数
     */
    public static void main(String[] args) {
        SpringApplication.run(SeataAtWareApplication.class, args);
    }
}
