package io.github.iweidujiang.lab11.user.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 积分服务 Feign 客户端。
 *
 * @author 苏渡苇
 */
@Service
@FeignClient("loyalty-service")
public interface LoyaltyService {

    /**
     * 获取用户当前积分。
     *
     * @param id 用户 ID
     * @return 当前积分
     */
    @GetMapping("/score/{id}")
    Integer getScore(@PathVariable("id") Long id);

    /**
     * 为用户增加积分。
     *
     * @param id        用户 ID
     * @param lastScore 当前积分
     * @param addScore  增加积分
     * @return 增加后的积分
     */
    @GetMapping("/addScore")
    Integer addScore(@RequestParam("id") Long id,
                     @RequestParam("lastScore") Integer lastScore,
                     @RequestParam("addScore") Integer addScore);
}
