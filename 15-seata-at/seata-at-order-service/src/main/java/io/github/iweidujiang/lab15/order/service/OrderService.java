package io.github.iweidujiang.lab15.order.service;

import io.github.iweidujiang.lab15.order.entity.Order;

/**
 * 订单业务接口。
 *
 * @author 苏渡苇
 */
public interface OrderService {

    /**
     * 创建订单（全局事务入口）。
     *
     * @param order 订单信息
     */
    void createOrder(Order order);
}
