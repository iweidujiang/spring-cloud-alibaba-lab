package io.github.iweidujiang.lab02.provider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务提供者 REST 接口。
 *
 * @author 苏渡苇
 */
@RestController
public class ProviderController {

    @Value("${server.port}")
    private String serverPort;

    /**
     * 返回当前服务实例端口，用于验证负载均衡。
     *
     * @return 端口信息
     */
    @GetMapping("/test-port")
    public String getServerPort() {
        return "Nacos Provider port:" + serverPort;
    }
}
