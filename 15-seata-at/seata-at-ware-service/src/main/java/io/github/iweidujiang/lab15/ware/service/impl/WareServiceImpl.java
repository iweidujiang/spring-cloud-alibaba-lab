package io.github.iweidujiang.lab15.ware.service.impl;

import io.github.iweidujiang.lab15.ware.mapper.WareMapper;
import io.github.iweidujiang.lab15.ware.service.WareService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 库存业务实现。
 *
 * @author 苏渡苇
 */
@Service
public class WareServiceImpl implements WareService {

    private static final Logger log = LoggerFactory.getLogger(WareServiceImpl.class);

    private final WareMapper wareMapper;

    /**
     * 构造方法。
     *
     * @param wareMapper 库存 Mapper
     */
    public WareServiceImpl(WareMapper wareMapper) {
        this.wareMapper = wareMapper;
    }

    /**
     * 扣减库存。
     *
     * @param skuId 商品 SKU ID
     */
    @Override
    public void deductStock(Long skuId) {
        log.info("开始扣减库存，skuId={}", skuId);
        wareMapper.deductStock(skuId);
    }
}
