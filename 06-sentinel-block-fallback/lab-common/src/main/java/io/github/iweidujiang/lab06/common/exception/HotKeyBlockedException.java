package io.github.iweidujiang.lab06.common.exception;

import com.alibaba.csp.sentinel.slots.block.BlockException;

/**
 * 热点参数限流异常，并提供 blockHandler 解耦处理逻辑。
 *
 * @author 苏渡苇
 */
public class HotKeyBlockedException extends RuntimeException {

    private final Object hotKey;

    /**
     * 构造热点参数限流异常。
     *
     * @param hotKey 热点参数值
     */
    public HotKeyBlockedException(Object hotKey) {
        super("热点参数 [" + hotKey + "] 限流！");
        this.hotKey = hotKey;
    }

    /**
     * getProduct 资源的 blockHandler，限流时抛出业务异常。
     *
     * @param userId         用户 ID
     * @param productId      商品 ID
     * @param categoryId     分类 ID
     * @param blockException Sentinel 限流异常
     * @return 不会正常返回，始终抛异常
     */
    public static String getProductBlockHandler(Long userId,
                                                Long productId,
                                                Integer categoryId,
                                                BlockException blockException) {
        throw new HotKeyBlockedException(userId);
    }

    /**
     * 获取热点参数值。
     *
     * @return 热点参数
     */
    public Object getHotKey() {
        return hotKey;
    }
}
