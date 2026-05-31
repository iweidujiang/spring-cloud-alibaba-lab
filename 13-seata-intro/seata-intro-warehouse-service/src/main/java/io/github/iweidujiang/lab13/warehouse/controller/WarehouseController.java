package io.github.iweidujiang.lab13.warehouse.controller;

import io.github.iweidujiang.lab13.warehouse.service.WarehouseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 库存接口。
 *
 * @author 苏渡苇
 */
@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    private final WarehouseService warehouseService;

    /**
     * 构造方法。
     *
     * @param warehouseService 库存服务
     */
    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    /**
     * 扣减库存。
     *
     * @param productId    商品 ID
     * @param quantity     扣减数量
     * @param simulateFail 是否模拟失败
     * @return 扣减结果
     */
    @GetMapping("/deduct")
    public Map<String, Object> deduct(@RequestParam Long productId,
                                      @RequestParam(defaultValue = "1") Integer quantity,
                                      @RequestParam(defaultValue = "false") boolean simulateFail) {
        int remain = warehouseService.deduct(productId, quantity, simulateFail);
        Map<String, Object> result = new HashMap<>();
        result.put("productId", productId);
        result.put("remainStock", remain);
        return result;
    }

    /**
     * 查询库存。
     *
     * @param productId 商品 ID
     * @return 库存信息
     */
    @GetMapping("/stock")
    public Map<String, Object> stock(@RequestParam Long productId) {
        Map<String, Object> result = new HashMap<>();
        result.put("productId", productId);
        result.put("stock", warehouseService.getStock(productId));
        return result;
    }
}
