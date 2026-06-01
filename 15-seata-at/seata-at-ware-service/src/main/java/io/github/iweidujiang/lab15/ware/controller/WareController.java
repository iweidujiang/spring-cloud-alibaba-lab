package io.github.iweidujiang.lab15.ware.controller;

import io.github.iweidujiang.lab15.ware.service.WareService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 库存接口。
 *
 * @author 苏渡苇
 */
@RestController
@RequestMapping("/ware")
public class WareController {

    private final WareService wareService;

    /**
     * 构造方法。
     *
     * @param wareService 库存服务
     */
    public WareController(WareService wareService) {
        this.wareService = wareService;
    }

    /**
     * 扣减库存。
     *
     * @param skuId 商品 SKU ID
     */
    @GetMapping("/deduct")
    public void deductStock(@RequestParam Long skuId) {
        wareService.deductStock(skuId);
    }
}
