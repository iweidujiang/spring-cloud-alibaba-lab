package io.github.iweidujiang.lab09.user.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户接口。
 *
 * @author 苏渡苇
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Value("${server.port}")
    private String serverPort;

    /**
     * 查询用户信息。
     *
     * @param id   用户 ID
     * @param home 网关过滤器添加的请求头
     * @return 用户信息
     */
    @GetMapping("/info/{id}")
    public Map<String, Object> getUserInfo(@PathVariable Long id,
                                           @RequestHeader(value = "X-Request-Home", required = false) String home) {
        Map<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("name", "用户" + id);
        result.put("serverPort", serverPort);
        if (home != null) {
            result.put("X-Request-Home", home);
        }
        return result;
    }
}
