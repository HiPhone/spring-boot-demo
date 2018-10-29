package org.hiphone.mybatis.entitys;

import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

import java.io.Serializable;
import java.util.Date;

/**
 * @author HiPhone
 */
@ApiModel(value = "UserDTO", description = "用户信息封装数据传输类")
@JSONType(orders = {"id" , "loginName", "password", "role", "createBy", "birthday", "createBy", "createTime", "updateBy", "updateTime"})
public class UserDTO implements Serializable {

    private static final long serialVersionUID = -1359515247894745832L;

    @ApiParam(value = "数据生成的唯一id", name = "id")
    private Long id ;

    @ApiModelProperty(value = "登陆名", name = "loginName")
    private String loginName;

    @ApiModelProperty(value = "登陆密码", name = "password")
    private String password;

    @ApiParam(value = "用户的角色", name = "role")
    private Integer role;

    @ApiParam(value = "角色创建人", name = "createBy")
    private String createBy;

    @ApiParam(value = "角色创建时间", name = "createTime")
    private Date createTime;

    @ApiParam(value = "角色更新人", name = "updateBy")
    private String updateBy;

    @ApiParam(value = "角色更新时间", name = "updateTime")
    private Date updateTime;

    public UserDTO() {}

    public UserDTO(Long id, String loginName, String password, Integer role, String createBy, Date createTime, String updateBy, Date updateTime) {
        this.id = id;
        this.loginName = loginName;
        this.password = password;
        this.role = role;
        this.createBy = createBy;
        this.createTime = createTime;
        this.updateBy = updateBy;
        this.updateTime = updateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
