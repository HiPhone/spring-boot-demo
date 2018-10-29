package org.hiphone.mybatis.mapper;

import org.hiphone.mybatis.entitys.UserDTO;

import java.util.List;

public interface UserMapper {

    /**
     * 获取数据库中所有的用户账户信息
     * @return list of user_account
     */
    List<UserDTO> listAllUserData();
}
