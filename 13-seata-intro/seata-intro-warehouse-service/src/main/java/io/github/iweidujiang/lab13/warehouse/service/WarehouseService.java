package io.github.iweidujiang.lab13.warehouse.service;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 内存库存服务，用于演示无分布式事务时的数据一致性问题。
 *
 * @author 苏渡苇
 */
@Service
public class WarehouseService {

    private final Map<Long, Integer> stockMap = new ConcurrentHashMap<>();

    /**
     * 构造方法，初始化示例库存。
     */
    public WarehouseService() {
        stockMap.put(1L, 100);
        stockMap.put(2L, 50);
    }

    /**
     * 扣减库存。
     *
     * @param productId 商品 ID
     * @param quantity  扣减数量
     * @param simulateFail 是否模拟扣减失败
     * @return 扣减后库存
     */
    public int deduct(Long productId, Integer quantity, boolean simulateFail) {
        if (simulateFail) {
            throw new IllegalStateException("模拟库存扣减失败");
        }
        Integer stock = stockMap.getOrDefault(productId, 0);
        if (stock < quantity) {
            throw new IllegalStateException("库存不足，productId=" + productId);
        }
        int remain = stock - quantity;
        stockMap.put(productId, remain);
        return remain;
    }

    /**
     * 查询库存。
     *
     * @param productId 商品 ID
     * @return 当前库存
     */
    public int getStock(Long productId) {
        return stockMap.getOrDefault(productId, 0);
    }
}
