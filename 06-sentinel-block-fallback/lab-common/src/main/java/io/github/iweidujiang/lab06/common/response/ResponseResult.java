package io.github.iweidujiang.lab06.common.response;

/**
 * 统一 API 返回结果。
 *
 * @param <T> 数据类型
 * @author 苏渡苇
 */
public class ResponseResult<T> {

    private String code;
    private String message;
    private T data;

    public ResponseResult() {
    }

    public ResponseResult(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 操作成功的返回。
     *
     * @param data 返回数据
     * @param <T>  数据类型
     * @return 成功响应
     */
    public static <T> ResponseResult<T> success(T data) {
        ResponseResult<T> result = new ResponseResult<>();
        result.setCode(ResponseCode.SUCCESS.getCode());
        result.setMessage(ResponseCode.SUCCESS.getMessage());
        result.setData(data);
        return result;
    }

    /**
     * 操作失败的返回。
     *
     * @param code    状态码
     * @param message 提示信息
     * @param <T>     数据类型
     * @return 失败响应
     */
    public static <T> ResponseResult<T> fail(String code, String message) {
        return new ResponseResult<>(code, message);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
