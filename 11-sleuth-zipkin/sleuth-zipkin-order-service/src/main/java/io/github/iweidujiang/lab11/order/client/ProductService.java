package io.github.iweidujiang.lab11.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

/**
 * 商品服务 Feign 客户端。
 *
 * @author 苏渡苇
 */
@Service
@FeignClient("product-service")
public interface ProductService {

    /**
     * 查询商品价格。
     *
     * @param id 商品 ID
     * @return 商品价格
     */
    @GetMapping("/product/price/{id}")
    BigDecimal getPrice(@PathVariable("id") Long id);
}
