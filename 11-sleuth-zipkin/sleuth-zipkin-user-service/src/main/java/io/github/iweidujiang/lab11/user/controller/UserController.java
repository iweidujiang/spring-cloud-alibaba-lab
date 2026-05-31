package io.github.iweidujiang.lab11.user.controller;

import io.github.iweidujiang.lab11.common.response.ResponseResult;
import io.github.iweidujiang.lab11.user.client.LoyaltyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户积分代理接口。
 *
 * @author 苏渡苇
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private LoyaltyService loyaltyService;

    /**
     * 查询用户积分。
     *
     * @param id 用户 ID
     * @return 统一返回结果
     */
    @GetMapping("/score/{id}")
    public ResponseResult<Integer> getScore(@PathVariable("id") Long id) {
        return ResponseResult.success(loyaltyService.getScore(id));
    }

    /**
     * 为用户增加积分。
     *
     * @param id        用户 ID
     * @param lastScore 当前积分
     * @param addScore  增加积分
     * @return 统一返回结果
     */
    @GetMapping("/addScore")
    public ResponseResult<Integer> addScore(@RequestParam Long id,
                                            @RequestParam Integer lastScore,
                                            @RequestParam Integer addScore) {
        return ResponseResult.success(loyaltyService.addScore(id, lastScore, addScore));
    }

    /**
     * 注入积分服务客户端。
     *
     * @param loyaltyService 积分服务 Feign 客户端
     */
    @Autowired
    public void setLoyaltyService(LoyaltyService loyaltyService) {
        this.loyaltyService = loyaltyService;
    }
}
