package io.github.iweidujiang.lab06.service.handler;

import io.github.iweidujiang.lab06.common.exception.HotKeyBlockedException;
import io.github.iweidujiang.lab06.common.response.ResponseCode;
import io.github.iweidujiang.lab06.common.response.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理。
 *
 * @author 苏渡苇
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 热点参数限流异常处理。
     *
     * @param e 热点参数限流异常
     * @return 统一失败响应
     */
    @ExceptionHandler(HotKeyBlockedException.class)
    public ResponseResult<String> hotKeyBlockedException(HotKeyBlockedException e) {
        LOGGER.warn("热点参数限流: {}", e.getHotKey());
        return ResponseResult.fail(ResponseCode.HOT_KEY_BLOCKED.getCode(), ResponseCode.HOT_KEY_BLOCKED.getMessage());
    }

    /**
     * 未知异常统一处理。
     *
     * @param e 异常对象
     * @return 统一失败响应
     */
    @ExceptionHandler(Exception.class)
    public ResponseResult<String> exception(Exception e) {
        LOGGER.error("未知异常", e);
        return ResponseResult.fail(ResponseCode.INTERNAL_ERROR.getCode(), ResponseCode.INTERNAL_ERROR.getMessage());
    }
}
