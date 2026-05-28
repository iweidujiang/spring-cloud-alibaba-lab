package io.github.iweidujiang.lab08.provider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 商品查询接口。
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
     * @param id 商品 ID
     * @return 商品信息
     */
    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable Long id) {
        return serverPort + "：" + PRODUCT_MAP.getOrDefault(id, "未知商品");
    }
}
