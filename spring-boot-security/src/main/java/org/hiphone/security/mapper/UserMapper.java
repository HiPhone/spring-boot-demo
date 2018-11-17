package org.hiphone.security.mapper;

import org.hiphone.security.entitys.UserDTO;

public interface UserMapper {

    /**
     * 通过登陆名获取用户密码，用于密码验证
     * @param loginName 登陆用户名
     * @return 用户密码
     */
    UserDTO getUserByLoginName(String loginName);
}
