package io.github.iweidujiang.lab11.product.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * 商品接口。
 *
 * @author 苏渡苇
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    /**
     * 查询商品价格。
     *
     * @param id 商品 ID
     * @return 商品价格
     */
    @GetMapping("/price/{id}")
    public BigDecimal getPrice(@PathVariable("id") Long id) {
        if (id == 1) {
            return new BigDecimal("5899");
        }
        return new BigDecimal("5999");
    }
}
