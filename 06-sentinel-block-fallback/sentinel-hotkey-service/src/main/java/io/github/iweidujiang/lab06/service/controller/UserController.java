package io.github.iweidujiang.lab06.service.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import io.github.iweidujiang.lab06.common.exception.HotKeyBlockedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 热点参数限流测试接口。
 *
 * @author 苏渡苇
 */
@RestController
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    /**
     * 查询商品，第一个参数 userId 可作为热点参数限流。
     *
     * @param userId     用户 ID
     * @param productId  商品 ID
     * @param categoryId 分类 ID
     * @return 查询结果
     */
    @GetMapping("/getProduct")
    @SentinelResource(
            value = "getProduct",
            blockHandlerClass = HotKeyBlockedException.class,
            blockHandler = "getProductBlockHandler",
            fallback = "getProductFallback"
    )
    public String getProduct(@RequestParam(value = "userId", required = false) Long userId,
                             @RequestParam(value = "productId", required = false) Long productId,
                             @RequestParam(value = "categoryId", required = false) Integer categoryId) {
        log.info("getProduct param userId={}, productId={}, categoryId={}", userId, productId, categoryId);
        if (userId != null && userId < 0) {
            throw new IllegalArgumentException("userId 不能为负数");
        }
        return "getProduct success";
    }

    /**
     * fallback 处理非 BlockException 的业务异常。
     *
     * @param userId     用户 ID
     * @param productId  商品 ID
     * @param categoryId 分类 ID
     * @param throwable  原始异常
     * @return 降级响应
     */
    public String getProductFallback(Long userId,
                                     Long productId,
                                     Integer categoryId,
                                     Throwable throwable) {
        return "getProduct fallback: " + throwable.getMessage();
    }
}
