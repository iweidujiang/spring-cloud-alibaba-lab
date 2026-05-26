package io.github.iweidujiang.lab01.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 订单业务服务，通过进程内调用 {@link UserService} 组装订单详情。
 *
 * @author 苏渡苇
 */
@Service
public class OrderService {

    private static final Map<Long, Long> ORDERS = new HashMap<>();

    static {
        ORDERS.put(1001L, 1L);
        ORDERS.put(1002L, 2L);
    }

    private final UserService userService;

    /**
     * 构造订单服务。
     *
     * @param userService 用户业务服务
     */
    public OrderService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 查询订单详情，并关联展示所属用户信息。
     *
     * @param orderId 订单 ID
     * @return 订单详情描述
     */
    public String getOrderDetail(Long orderId) {
        Long userId = ORDERS.get(orderId);
        if (userId == null) {
            return "订单不存在: " + orderId;
        }
        String userName = userService.getUserName(userId);
        return String.format("订单[%d] 属于用户[%d-%s]（单体应用：进程内直接调用 UserService）",
                orderId, userId, userName);
    }
}
