package io.github.iweidujiang.lab01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 单体应用启动类，演示用户与订单模块部署在同一进程中。
 *
 * @author 苏渡苇
 */
@SpringBootApplication
public class MonolithApplication {

    /**
     * 应用入口。
     *
     * @param args 启动参数
     */
    public static void main(String[] args) {
        SpringApplication.run(MonolithApplication.class, args);
    }
}
