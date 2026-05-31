package io.github.iweidujiang.lab11.order.controller;

import io.github.iweidujiang.lab11.order.client.ProductService;
import io.github.iweidujiang.lab11.order.client.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * 订单接口。
 *
 * @author 苏渡苇
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    private UserService userService;

    private ProductService productService;

    /**
     * 创建订单并为用户增加积分。
     *
     * @param userId    用户 ID
     * @param productId 商品 ID
     * @return 下单结果
     */
    @GetMapping("/create")
    public String createOrder(@RequestParam("userId") Long userId,
                              @RequestParam("productId") Long productId) {
        log.info("创建订单请求，userId={}, productId={}", userId, productId);
        BigDecimal price = productService.getPrice(productId);
        log.info("结果 price={}", price);
        Integer currentScore = userService.getScore(userId).getData();
        log.info("结果 currentScore={}", currentScore);
        Integer addScore = price.intValue();
        Integer finalScore = userService.addScore(userId, currentScore, addScore).getData();
        log.info("下单成功，用户 id={} 最终积分：{}", userId, finalScore);
        return "下单成功，用户 id=" + userId + " 最终积分：" + finalScore;
    }

    /**
     * 注入用户服务客户端。
     *
     * @param userService 用户服务 Feign 客户端
     */
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 注入商品服务客户端。
     *
     * @param productService 商品服务 Feign 客户端
     */
    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}
