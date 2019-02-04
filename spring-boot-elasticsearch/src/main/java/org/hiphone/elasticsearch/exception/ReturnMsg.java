package org.hiphone.elasticsearch.exception;

import lombok.Getter;

/**
 * @author HiPhone
 */
public enum ReturnMsg {

    SUCCESS(0, "服务调用成功"),
    UNKNOWN_ERROR(9999, "未知错误请排查"),
    INDEX_EXISTS(1003, "参数错误！若创建索引则表示索引已存在，无需创建"),
    INDEX_NOT_FOUND(1004, "页面不存在！若操作es则表示索引或数据不存在"),
    METHOD_NOT_SUPPORT(1005, "请求方法不正确")
    ;

    @Getter
    private String message;

    @Getter
    private int code;

    ReturnMsg(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
