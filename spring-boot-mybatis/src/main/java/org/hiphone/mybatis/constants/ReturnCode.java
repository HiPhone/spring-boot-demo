package org.hiphone.mybatis.constants;

import lombok.Getter;

/**
 * @author HiPhone
 */
public enum ReturnCode {

    SUCCESS(0, "服务调用成功"),
    UNKNOWN_ERROR(9999, "未知错误请排查"),
    LOGIN_FAIL(1001, "用户名或密码错误"),
    UNAUTHORIZED(1003, "服务调用未授权"),
    BUSY_ERROR(1004, "服务繁忙，请稍后尝试"),
    SQL_ERROR(1005, "数据库异常"),
    INSERT_ERROR(1006, "插入数据失败"),
    UPDATE_ERROR(1007, "更新数据失败"),
    DELETE_ERROR(1008, "删除数据失败");

    @Getter
    private String message;

    @Getter
    private int code;

    private ReturnCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

}