package org.hiphone.mybatis.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 登陆信息，包括loginName和password
 * @author HiPhone
 */
@ApiModel(value = "LoginModel", description = "登陆信息的封装类")
public class LoginModel implements Serializable {

    private static final long serialVersionUID = -5441744036193888417L;

    @ApiModelProperty(value = "登陆名", name = "loginName")
    private String loginName;

    @ApiModelProperty(value = "登陆密码", name = "password")
    private String password;

    public LoginModel() {}

    public LoginModel(String loginName, String password) {
        this.loginName = loginName;
        this.password = password;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginModel{" +
                "loginName='" + loginName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
