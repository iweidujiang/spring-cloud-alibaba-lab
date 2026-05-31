package io.github.iweidujiang.lab11.loyalty.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户积分接口。
 *
 * @author 苏渡苇
 */
@RestController
public class LoyaltyController {

    private static final Logger log = LoggerFactory.getLogger(LoyaltyController.class);

    /**
     * 获取用户当前积分。
     *
     * @param id 用户 ID
     * @return 当前积分
     */
    @GetMapping("/score/{id}")
    public Integer getScore(@PathVariable("id") Long id) {
        log.info("获取用户 id={} 当前积分", id);
        return 1800;
    }

    /**
     * 为当前用户增加积分。
     *
     * @param id        用户 ID
     * @param lastScore 用户当前积分
     * @param addScore  要增加的积分
     * @return 增加后的积分
     */
    @GetMapping("/addScore")
    public Integer addScore(@RequestParam("id") Long id,
                            @RequestParam("lastScore") Integer lastScore,
                            @RequestParam("addScore") Integer addScore) {
        log.info("用户 id={} 增加 {} 积分", id, addScore);
        return lastScore + addScore;
    }
}
