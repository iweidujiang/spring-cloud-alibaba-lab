package io.github.iweidujiang.lab08.consumer.handler;

import io.github.iweidujiang.lab08.common.exception.BusinessException;
import io.github.iweidujiang.lab08.common.response.ResponseCode;
import io.github.iweidujiang.lab08.common.response.ResponseResult;
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
     * 业务异常统一处理。
     *
     * @param e 业务异常
     * @return 统一失败响应
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseResult<String> businessException(BusinessException e) {
        LOGGER.info("code={}, message={}", e.getCode(), e.getMessage());
        return ResponseResult.fail(e.getCode(), e.getMessage());
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
