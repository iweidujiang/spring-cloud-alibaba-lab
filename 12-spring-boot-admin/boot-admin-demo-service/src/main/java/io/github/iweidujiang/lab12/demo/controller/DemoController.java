package io.github.iweidujiang.lab12.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 示例业务接口。
 *
 * @author 苏渡苇
 */
@RestController
public class DemoController {

    /**
     * 健康检查用示例接口。
     *
     * @return 示例数据
     */
    @GetMapping("/demo/info")
    public Map<String, Object> info() {
        Map<String, Object> result = new HashMap<>();
        result.put("service", "boot-admin-demo-service");
        result.put("message", "Spring Boot Admin 监控示例");
        return result;
    }
}
