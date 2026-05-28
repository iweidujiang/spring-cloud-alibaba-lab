package io.github.iweidujiang.lab07.consumer.controller;

import io.github.iweidujiang.lab07.consumer.client.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * OpenFeign 调用测试接口。
 *
 * @author 苏渡苇
 */
@RestController
public class ProductController {

    private final ProductService productService;

    /**
     * 构造控制器。
     *
     * @param productService Feign 客户端
     */
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * 通过 OpenFeign 远程查询商品。
     *
     * @param id           商品 ID
     * @param delaySeconds 传递给提供者的延迟秒数
     * @return 远程调用结果
     */
    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable("id") Long id,
                             @RequestParam(value = "delay", defaultValue = "0") int delaySeconds) {
        return productService.getProductById(id, delaySeconds);
    }
}
