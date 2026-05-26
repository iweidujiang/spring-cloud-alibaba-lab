package io.github.iweidujiang.lab03.controller;

import io.github.iweidujiang.lab03.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * Sentinel 限流测试接口。
 *
 * @author 苏渡苇
 */
@RestController
public class TestController {

    private final UserService userService;

    /**
     * 构造测试控制器。
     *
     * @param userService 用户业务服务
     */
    public TestController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 测试接口 A，模拟耗时操作，用于并发线程数限流演示。
     *
     * @return 响应内容
     */
    @GetMapping("/test-a")
    public String testSentinelA() {
        try {
            TimeUnit.MILLISECONDS.sleep(800);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        String user = userService.getUser();
        return "hello test-a user:" + user + "!";
    }

    /**
     * 测试接口 B，与 test-a 共用 getUser 资源，用于关联/链路限流演示。
     *
     * @return 响应内容
     */
    @GetMapping("/test-b")
    public String testSentinelB() {
        String user = userService.getUser();
        return "hello test-b user:" + user + "!";
    }

    /**
     * 测试接口 C，用于 Warm Up 预热限流演示。
     *
     * @return 响应内容
     */
    @GetMapping("/test-c")
    public String testSentinelC() {
        return "hello, sentinel C!";
    }
}
