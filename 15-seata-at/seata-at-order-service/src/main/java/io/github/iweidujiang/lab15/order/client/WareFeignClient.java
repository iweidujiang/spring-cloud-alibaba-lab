package io.github.iweidujiang.lab15.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 库存服务 Feign 客户端。
 *
 * @author 苏渡苇
 */
@FeignClient("seata-ware-service")
public interface WareFeignClient {

    /**
     * 远程扣减库存。
     *
     * @param skuId 商品 SKU ID
     */
    @GetMapping("/ware/deduct")
    void deductStock(@RequestParam("skuId") Long skuId);
}
