package io.github.iweidujiang.lab08.consumer.client;

import io.github.iweidujiang.lab08.consumer.fallback.ProductServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 远程调用 nacos-provider 的 Feign 客户端。
 *
 * @author 苏渡苇
 */
@FeignClient(name = "nacos-provider", fallback = ProductServiceImpl.class)
public interface ProductService {

    /**
     * 调用远程 /product/{id} 接口。
     *
     * @param id 商品 ID
     * @return 商品信息
     */
    @GetMapping("/product/{id}")
    String getProductById(@PathVariable("id") Long id);
}
