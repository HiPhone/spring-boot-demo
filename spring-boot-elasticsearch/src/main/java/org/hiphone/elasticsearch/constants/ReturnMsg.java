package org.hiphone.elasticsearch.constants;

import lombok.Getter;

/**
 * @author HiPhone
 */
public enum ReturnMsg {

    SUCCESS(0, "服务调用成功"),
    UNKNOWN_ERROR(9999, "未知错误请排查"),
    INDEX_EXISTS(1005, "索引已存在，无需创建"),
    INDEX_NOT_FOUND(1005, "索引或数据不存在");

    @Getter
    private String message;

    @Getter
    private int code;

    ReturnMsg(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
