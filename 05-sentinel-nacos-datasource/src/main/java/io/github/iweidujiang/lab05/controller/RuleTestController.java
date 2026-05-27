package io.github.iweidujiang.lab05.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Sentinel 流控规则测试接口，规则从 Nacos 配置中心加载。
 *
 * @author 苏渡苇
 */
@RestController
public class RuleTestController {

    /**
     * getUser 资源，对应 Nacos 中 sentinelFlowRule.json 的 getUser 规则。
     *
     * @return 响应内容
     */
    @GetMapping("/getUser")
    @SentinelResource("getUser")
    public String getUser() {
        return "getUser success!";
    }

    /**
     * getOrder 资源，对应 Nacos 中 sentinelFlowRule.json 的 getOrder 规则。
     *
     * @return 响应内容
     */
    @GetMapping("/getOrder")
    @SentinelResource("getOrder")
    public String getOrder() {
        return "getOrder success!";
    }
}
