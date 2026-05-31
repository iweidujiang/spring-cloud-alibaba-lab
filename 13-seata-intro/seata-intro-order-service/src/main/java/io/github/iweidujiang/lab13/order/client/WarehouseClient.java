package io.github.iweidujiang.lab13.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * 库存服务 Feign 客户端。
 *
 * @author 苏渡苇
 */
@FeignClient("warehouse-service")
public interface WarehouseClient {

    /**
     * 远程扣减库存。
     *
     * @param productId    商品 ID
     * @param quantity     扣减数量
     * @param simulateFail 是否模拟失败
     * @return 扣减结果
     */
    @GetMapping("/warehouse/deduct")
    Map<String, Object> deduct(@RequestParam("productId") Long productId,
                               @RequestParam("quantity") Integer quantity,
                               @RequestParam("simulateFail") boolean simulateFail);
}
