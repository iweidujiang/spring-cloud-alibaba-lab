package io.github.iweidujiang.lab08.consumer.fallback;

import io.github.iweidujiang.lab08.common.exception.BusinessException;
import io.github.iweidujiang.lab08.common.response.ResponseCode;
import io.github.iweidujiang.lab08.consumer.client.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * ProductService 降级实现。
 *
 * @author 苏渡苇
 */
@Component
public class ProductServiceImpl implements ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    /**
     * 远程调用失败时的降级处理。
     *
     * @param id 商品 ID
     * @return 不会正常返回
     */
    @Override
    public String getProductById(Long id) {
        log.error("调用接口 getProduct 失败，id={}", id);
        throw new BusinessException(ResponseCode.RPC_ERROR.getCode(), ResponseCode.RPC_ERROR.getMessage());
    }
}
