package io.github.iweidujiang.lab15.ware.service;

/**
 * 库存业务接口。
 *
 * @author 苏渡苇
 */
public interface WareService {

    /**
     * 扣减库存。
     *
     * @param skuId 商品 SKU ID
     */
    void deductStock(Long skuId);
}
