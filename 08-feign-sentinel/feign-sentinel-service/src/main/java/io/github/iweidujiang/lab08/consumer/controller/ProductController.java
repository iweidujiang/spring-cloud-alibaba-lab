package io.github.iweidujiang.lab08.consumer.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import io.github.iweidujiang.lab08.common.exception.BusinessException;
import io.github.iweidujiang.lab08.consumer.client.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * OpenFeign + Sentinel 测试接口。
 *
 * @author 苏渡苇
 */
@RestController
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

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
     * 通过 Feign 远程查询商品，并受 Sentinel 资源保护。
     *
     * @param id 商品 ID
     * @return 查询结果
     */
    @GetMapping("/product/{id}")
    @SentinelResource(
            value = "getProduct",
            blockHandler = "getProductBlock",
            fallback = "getProductFallback"
    )
    public String getProduct(@PathVariable("id") Long id) {
        return productService.getProductById(id);
    }

    /**
     * Sentinel 限流 blockHandler。
     *
     * @param id 商品 ID
     * @param e  限流异常
     * @return 不会正常返回
     */
    public String getProductBlock(Long id, BlockException e) {
        log.error("访问资源 getProduct 被限流，id={}", id);
        throw new BusinessException("C0002", "访问资源 getProduct 被限流");
    }

    /**
     * Sentinel fallback 降级处理。
     *
     * @param id 商品 ID
     * @return 降级提示
     */
    public String getProductFallback(Long id) {
        log.error("访问资源 getProduct fallback, id={}", id);
        return "请稍后重试";
    }
}
