package io.github.iweidujiang.lab02.consumer.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * 服务消费者 REST 接口。
 *
 * @author 苏渡苇
 */
@RestController
public class ConsumerController {

    @Resource
    private RestTemplate restTemplate;

    @Value("${service-url.nacos-provider-service}")
    private String serviceUrl;

    /**
     * 通过 LoadBalancer 调用服务提供者接口。
     *
     * @return 服务提供者返回内容
     */
    @GetMapping("/consume")
    public String consume() {
        return restTemplate.getForObject(serviceUrl + "/test-port", String.class);
    }
}
