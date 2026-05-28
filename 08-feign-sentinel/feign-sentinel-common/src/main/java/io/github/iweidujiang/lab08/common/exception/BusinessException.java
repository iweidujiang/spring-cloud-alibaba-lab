package io.github.iweidujiang.lab08.common.exception;

/**
 * 业务异常。
 *
 * @author 苏渡苇
 */
public class BusinessException extends RuntimeException {

    private final String code;
    private final String message;

    /**
     * 构造业务异常。
     *
     * @param code    错误码
     * @param message 错误信息
     */
    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    /**
     * 获取错误码。
     *
     * @return 错误码
     */
    public String getCode() {
        return code;
    }

    /**
     * 获取错误信息。
     *
     * @return 错误信息
     */
    @Override
    public String getMessage() {
        return message;
    }
}
