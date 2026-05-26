package io.github.iweidujiang.lab01.controller;

import io.github.iweidujiang.lab01.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单模块 REST 接口。
 *
 * @author 苏渡苇
 */
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    /**
     * 构造订单控制器。
     *
     * @param orderService 订单业务服务
     */
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * 根据订单 ID 查询订单详情。
     *
     * @param id 订单 ID
     * @return 订单详情描述
     */
    @GetMapping("/{id}")
    public String getOrder(@PathVariable Long id) {
        return orderService.getOrderDetail(id);
    }
}
