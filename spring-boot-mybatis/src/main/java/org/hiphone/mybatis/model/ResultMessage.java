package org.hiphone.mybatis.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author HiPhone
 */
@ApiModel(value = "ResultMessage", description = "将返回值封装，统一放回的数据报文")
public class ResultMessage implements Serializable {

    private static final long serialVersionUID = -7927576705278070943L;

    @ApiModelProperty(value = "返回码", name = "code")
    private Integer code;

    @ApiModelProperty(value = "返回码的解释", name = "message")
    private String message;

    @ApiModelProperty(value = "返回的数据", name = "data")
    private Object data;

    public ResultMessage() {}

    public ResultMessage(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
