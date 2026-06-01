package io.github.iweidujiang.lab15.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 订单服务启动类（Seata TM）。
 *
 * @author 苏渡苇
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("io.github.iweidujiang.lab15.order.mapper")
public class SeataAtOrderApplication {

    /**
     * 应用入口。
     *
     * @param args 启动参数
     */
    public static void main(String[] args) {
        SpringApplication.run(SeataAtOrderApplication.class, args);
    }
}
