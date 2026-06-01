package io.github.iweidujiang.lab15.order.controller;

import io.github.iweidujiang.lab15.order.entity.Order;
import io.github.iweidujiang.lab15.order.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单接口。
 *
 * @author 苏渡苇
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    /**
     * 构造方法。
     *
     * @param orderService 订单服务
     */
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * 创建订单。
     *
     * @param skuId 商品 SKU ID
     * @return 处理结果
     */
    @GetMapping("/create")
    public String create(@RequestParam(defaultValue = "10086") Long skuId) {
        Order order = new Order();
        order.setSkuId(skuId);
        orderService.createOrder(order);
        return "下单成功";
    }
}
