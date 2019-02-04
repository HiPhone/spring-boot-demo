package org.hiphone.security.exception;

import lombok.Getter;

/**
 * 返回码配置类
 * @author HiPhone
 */
public enum ReturnMsg {

    SUCCESS(0, "服务调用成功"),
    UNKNOWN_ERROR(9999, "未知错误请排查"),
    LOGIN_FAIL(1001, "用户名或密码错误"),
    LOGIN_SUCCESS(1002, "登陆成功"),
    UNAUTHORIZED(1001, "服务调用未授权"),
    FORBIDDEN(1003, "未登录，无法访问"),
    SQL_ERROR(1005, "数据库异常"),
    BUSY_ERROR(1004, "服务繁忙，请稍后尝试");

    @Getter
    private String message;

    @Getter
    private int code;

    ReturnMsg(int code, String message) {
        this.code = code;
        this.message = message;
    }
}