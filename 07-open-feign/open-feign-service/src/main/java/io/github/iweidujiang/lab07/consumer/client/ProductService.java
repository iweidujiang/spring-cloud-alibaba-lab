package io.github.iweidujiang.lab07.consumer.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 远程调用 nacos-provider 服务的 Feign 客户端。
 *
 * @author 苏渡苇
 */
@FeignClient(name = "nacos-provider")
public interface ProductService {

    /**
     * 调用远程服务 nacos-provider 的 /product/{id} 接口。
     *
     * @param id           商品 ID
     * @param delaySeconds 模拟延迟秒数
     * @return 商品信息
     */
    @GetMapping("/product/{id}")
    String getProductById(@PathVariable("id") Long id,
                          @RequestParam(value = "delay", defaultValue = "0") int delaySeconds);
}
