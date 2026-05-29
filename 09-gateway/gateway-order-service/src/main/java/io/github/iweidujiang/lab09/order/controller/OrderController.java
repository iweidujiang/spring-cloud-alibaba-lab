package io.github.iweidujiang.lab09.order.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 订单接口。
 *
 * @author 苏渡苇
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Value("${server.port}")
    private String serverPort;

    /**
     * 查询订单信息。
     *
     * @param id 订单 ID
     * @return 订单信息
     */
    @GetMapping("/info/{id}")
    public Map<String, Object> getOrderInfo(@PathVariable Long id) {
        Map<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("desc", "订单" + id);
        result.put("serverPort", serverPort);
        return result;
    }
}
