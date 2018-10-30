package org.hiphone.mybatis.mapper;

import org.hiphone.mybatis.entitys.UserDTO;

import java.util.List;

public interface UserMapper {

    /**
     * 通过用户的id获取用户的信息
     * @param id 用户id
     * @return 数据库返回值
     */
    UserDTO getUserById(Long id);

    /**
     * 获取数据库中所有的用户账户信息
     * @return list of user_account
     */
    List<UserDTO> listAllUserData();

    /**
     * 通过用户的id删除用户信息
     * @param id 用户id
     * @return 数据返回码
     */
    Integer deleteUserById(Long id);

    /**
     * 插入新的用户信息
     * @param user 用户信息封装
     * @return 数据库返回码
     */
    Integer insertNewUser(UserDTO user);

    /**
     * 通过user的id更新用户信息
     * @param user 用户信息
     * @return 数据库返回值
     */
    Integer updateUserById(UserDTO user);
}
