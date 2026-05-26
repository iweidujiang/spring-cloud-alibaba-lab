package io.github.iweidujiang.lab02.configclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 配置中心演示接口，支持配置动态刷新。
 *
 * @author 苏渡苇
 */
@RestController
@RefreshScope
public class ConfigController {

    @Value("${config.info}")
    private String configInfo;

    /**
     * 读取 Nacos 配置中心中的 config.info 值。
     *
     * @return 配置内容
     */
    @GetMapping("/info")
    public String getInfo() {
        return configInfo;
    }
}
