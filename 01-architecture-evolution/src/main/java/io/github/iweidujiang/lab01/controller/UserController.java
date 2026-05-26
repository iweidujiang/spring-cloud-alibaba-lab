package io.github.iweidujiang.lab01.controller;

import io.github.iweidujiang.lab01.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户模块 REST 接口。
 *
 * @author 苏渡苇
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    /**
     * 构造用户控制器。
     *
     * @param userService 用户业务服务
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 根据用户 ID 查询用户信息。
     *
     * @param id 用户 ID
     * @return 用户信息描述
     */
    @GetMapping("/{id}")
    public String getUser(@PathVariable Long id) {
        return "用户: " + userService.getUserName(id);
    }
}
