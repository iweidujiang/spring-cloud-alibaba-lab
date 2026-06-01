package io.github.iweidujiang.lab15.order.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import io.github.iweidujiang.lab15.order.client.WareFeignClient;
import io.github.iweidujiang.lab15.order.entity.Order;
import io.github.iweidujiang.lab15.order.mapper.OrderMapper;
import io.github.iweidujiang.lab15.order.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 订单业务实现。
 *
 * @author 苏渡苇
 */
@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final OrderMapper orderMapper;

    private final WareFeignClient wareFeignClient;

    /**
     * 构造方法。
     *
     * @param orderMapper     订单 Mapper
     * @param wareFeignClient 库存 Feign 客户端
     */
    public OrderServiceImpl(OrderMapper orderMapper, WareFeignClient wareFeignClient) {
        this.orderMapper = orderMapper;
        this.wareFeignClient = wareFeignClient;
    }

    /**
     * 创建订单，使用 Seata 全局事务协调跨库操作。
     *
     * @param order 订单信息
     */
    @Override
    @GlobalTransactional
    public void createOrder(Order order) {
        log.info("开始扣减库存，skuId={}", order.getSkuId());
        wareFeignClient.deductStock(order.getSkuId());
        log.info("扣减库存完成，skuId={}", order.getSkuId());

        order.setOrderSn(IdWorker.getTimeId());
        order.setCreateTime(new Date());

        log.info("开始创建订单: {}", order);
        log.error("此处故意抛异常 order.getId() 时为 null，模拟分布式事务回滚：{}", order.getId().toString());
        orderMapper.insert(order);

        log.info("创建订单完成");
    }
}
