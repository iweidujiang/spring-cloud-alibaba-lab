package io.github.iweidujiang.lab01.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户业务服务，提供进程内的用户信息查询能力。
 *
 * @author 苏渡苇
 */
@Service
public class UserService {

    private static final Map<Long, String> USERS = new HashMap<>();

    static {
        USERS.put(1L, "张三");
        USERS.put(2L, "李四");
    }

    /**
     * 根据用户 ID 查询用户名。
     *
     * @param userId 用户 ID
     * @return 用户名，不存在时返回「未知用户」
     */
    public String getUserName(Long userId) {
        return USERS.getOrDefault(userId, "未知用户");
    }
}
