package io.github.iweidujiang.lab06.service.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.iweidujiang.lab06.common.response.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 全局统一响应包装。
 *
 * @author 苏渡苇
 */
@RestControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice<Object> {

    private ObjectMapper objectMapper;

    /**
     * 是否开启统一响应包装。
     *
     * @param methodParameter 方法参数
     * @param converterType   消息转换器类型
     * @return 始终开启
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    /**
     * 在响应写出前包装为 {@link ResponseResult}。
     *
     * @param body                  原始响应体
     * @param returnType            返回类型
     * @param selectedContentType   内容类型
     * @param selectedConverterType 转换器类型
     * @param request               请求
     * @param response              响应
     * @return 包装后的响应体
     */
    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {
        if (body instanceof String) {
            try {
                return objectMapper.writeValueAsString(ResponseResult.success(body));
            } catch (JsonProcessingException e) {
                throw new IllegalStateException("序列化统一响应失败", e);
            }
        }
        if (body instanceof ResponseResult) {
            return body;
        }
        return ResponseResult.success(body);
    }

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
}
