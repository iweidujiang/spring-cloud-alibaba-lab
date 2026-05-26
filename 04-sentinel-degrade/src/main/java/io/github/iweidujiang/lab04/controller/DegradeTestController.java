package io.github.iweidujiang.lab04.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * Sentinel 熔断降级测试接口。
 *
 * @author 苏渡苇
 */
@RestController
public class DegradeTestController {

    /**
     * 慢调用比例熔断演示：随机睡眠 0~1000ms，超过 RT 阈值即为慢调用。
     *
     * @return 响应内容
     */
    @GetMapping("/testSlowRate")
    public String testSlowRate() {
        int sleepMillis = (int) (Math.random() * 1000);
        try {
            TimeUnit.MILLISECONDS.sleep(sleepMillis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "testSlowRate success!";
    }

    /**
     * 异常比例熔断演示：id 为 0 时抛出业务异常。
     *
     * @param id 测试参数
     * @return 响应内容
     */
    @GetMapping("/testExceptionRate")
    public String testExceptionRate(@RequestParam Integer id) {
        if (id == 0) {
            throw new RuntimeException("id 不能等于0！");
        }
        return "testExceptionRate success!";
    }

    /**
     * 异常数熔断演示：id 为 0 时抛出业务异常。
     *
     * @param id 测试参数
     * @return 响应内容
     */
    @GetMapping("/testException")
    public String testException(@RequestParam Integer id) {
        if (id == 0) {
            throw new RuntimeException("id 不能等于0！");
        }
        return "testException success!";
    }
}
