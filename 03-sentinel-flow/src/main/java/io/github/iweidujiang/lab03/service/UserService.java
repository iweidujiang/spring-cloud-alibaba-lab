package io.github.iweidujiang.lab03.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

/**
 * 用户业务服务，演示 Sentinel 链路限流资源。
 *
 * @author 苏渡苇
 */
@Service
public class UserService {

    /**
     * 获取用户信息，该方法被标记为 Sentinel 资源。
     *
     * @return 用户名
     */
    @SentinelResource(value = "getUser")
    public String getUser() {
        return "demo-user";
    }
}
