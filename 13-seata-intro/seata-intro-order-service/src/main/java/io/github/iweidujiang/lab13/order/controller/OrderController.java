package io.github.iweidujiang.lab13.order.controller;

import io.github.iweidujiang.lab13.order.client.WarehouseClient;
import io.github.iweidujiang.lab13.order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;

    private final WarehouseClient warehouseClient;

    /**
     * 构造方法。
     *
     * @param orderService     订单服务
     * @param warehouseClient  库存 Feign 客户端
     */
    public OrderController(OrderService orderService, WarehouseClient warehouseClient) {
        this.orderService = orderService;
        this.warehouseClient = warehouseClient;
    }

    /**
     * 创建订单并远程扣减库存（无分布式事务协调）。
     *
     * @param productId    商品 ID
     * @param quantity     购买数量
     * @param simulateFail 是否模拟库存扣减失败
     * @return 处理结果
     */
    @GetMapping("/create")
    public Map<String, Object> create(@RequestParam Long productId,
                                      @RequestParam(defaultValue = "1") Integer quantity,
                                      @RequestParam(defaultValue = "false") boolean simulateFail) {
        Long orderId = orderService.createOrder(productId);
        log.info("本地订单已创建，orderId={}", orderId);
        try {
            Map<String, Object> deductResult = warehouseClient.deduct(productId, quantity, simulateFail);
            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("orderId", orderId);
            result.put("deductResult", deductResult);
            result.put("message", "下单成功");
            return result;
        } catch (Exception ex) {
            log.error("库存扣减失败，但本地订单已创建，orderId={}", orderId, ex);
            Map<String, Object> result = new HashMap<>();
            result.put("success", false);
            result.put("orderId", orderId);
            result.put("orderCount", orderService.countOrders());
            result.put("message", "库存扣减失败，本地订单已存在，数据不一致");
            return result;
        }
    }

    /**
     * 查询当前已创建订单信息。
     *
     * @return 订单统计
     */
    @GetMapping("/list")
    public Map<String, Object> list() {
        Map<String, Object> result = new HashMap<>();
        result.put("orderCount", orderService.countOrders());
        result.put("orderIds", orderService.listOrderIds());
        return result;
    }
}
