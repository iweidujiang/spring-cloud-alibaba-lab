package io.github.iweidujiang.lab11.order.client;

import io.github.iweidujiang.lab11.common.response.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户服务 Feign 客户端。
 *
 * @author 苏渡苇
 */
@Service
@FeignClient("user-service")
public interface UserService {

    /**
     * 查询用户积分。
     *
     * @param id 用户 ID
     * @return 统一返回结果
     */
    @GetMapping("/user/score/{id}")
    ResponseResult<Integer> getScore(@PathVariable("id") Long id);

    /**
     * 为用户增加积分。
     *
     * @param id        用户 ID
     * @param lastScore 当前积分
     * @param addScore  增加积分
     * @return 统一返回结果
     */
    @GetMapping("/user/addScore")
    ResponseResult<Integer> addScore(@RequestParam("id") Long id,
                                     @RequestParam("lastScore") Integer lastScore,
                                     @RequestParam("addScore") Integer addScore);
}
