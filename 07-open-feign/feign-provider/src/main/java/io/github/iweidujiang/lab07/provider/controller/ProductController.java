package io.github.iweidujiang.lab07.provider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 商品查询接口，供 OpenFeign 远程调用。
 *
 * @author 苏渡苇
 */
@RestController
public class ProductController {

    private static final Map<Long, String> PRODUCT_MAP = new HashMap<>();

    static {
        PRODUCT_MAP.put(1L, "香飘飘奶茶");
        PRODUCT_MAP.put(2L, "雀巢咖啡");
        PRODUCT_MAP.put(3L, "百事可乐");
    }

    @Value("${server.port}")
    private String serverPort;

    /**
     * 根据 ID 查询商品。
     *
     * @param id           商品 ID
     * @param delaySeconds 模拟慢调用延迟秒数，用于超时测试
     * @return 商品信息
     */
    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable Long id,
                             @RequestParam(value = "delay", defaultValue = "0") int delaySeconds) {
        if (delaySeconds > 0) {
            try {
                TimeUnit.SECONDS.sleep(delaySeconds);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return serverPort + "：" + PRODUCT_MAP.getOrDefault(id, "未知商品");
    }
}
