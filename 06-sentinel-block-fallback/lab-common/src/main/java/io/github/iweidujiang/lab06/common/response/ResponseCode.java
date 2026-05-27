package io.github.iweidujiang.lab06.common.response;

/**
 * 统一响应状态码。
 *
 * @author 苏渡苇
 */
public enum ResponseCode {

    /** 操作成功 */
    SUCCESS("00000", "操作成功"),

    /** 系统内部异常 */
    INTERNAL_ERROR("B0001", "系统执行出错"),

    /** 热点参数限流 */
    HOT_KEY_BLOCKED("B0002", "热点参数限流");

    private final String code;
    private final String message;

    ResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 获取状态码。
     *
     * @return 状态码
     */
    public String getCode() {
        return code;
    }

    /**
     * 获取提示信息。
     *
     * @return 提示信息
     */
    public String getMessage() {
        return message;
    }
}
