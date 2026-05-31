package io.github.iweidujiang.lab13.order.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 内存订单服务，用于演示本地事务与远程调用不一致问题。
 *
 * @author 苏渡苇
 */
@Service
public class OrderService {

    private final AtomicLong idGenerator = new AtomicLong(1);

    private final List<Long> createdOrderIds = Collections.synchronizedList(new ArrayList<>());

    /**
     * 创建订单并记录订单 ID。
     *
     * @param productId 商品 ID
     * @return 订单 ID
     */
    public Long createOrder(Long productId) {
        Long orderId = idGenerator.getAndIncrement();
        createdOrderIds.add(orderId);
        return orderId;
    }

    /**
     * 查询已创建订单数量。
     *
     * @return 订单数量
     */
    public int countOrders() {
        return createdOrderIds.size();
    }

    /**
     * 查询全部订单 ID。
     *
     * @return 订单 ID 列表
     */
    public List<Long> listOrderIds() {
        return new ArrayList<>(createdOrderIds);
    }
}
