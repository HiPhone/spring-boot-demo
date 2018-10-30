package org.hiphone.mybatis.service;

import org.hiphone.mybatis.entitys.UserDTO;
import org.hiphone.mybatis.model.ResultMessage;

public interface UserService {

    /**
     * 返回数据库中user_account表中所有的数据
     * @return resultMessage
     */
    ResultMessage listAllUserData();

    /**
     * 通过id获取用户信息
     * @param id user id
     * @return resultMessage
     */
    ResultMessage getUserById(Long id);

    /**
     * 通过id删除用户信息
     * @param id 用户id
     * @return resultMessage
     */
    ResultMessage deleteUserById(Long id);

    /**
     *  添加用户信息
     * @param user 用户信息
     * @return resultMessage
     */
    ResultMessage insertNewUser(UserDTO user);

    /**
     * 更新用户信息
     * @param user 用户信息
     * @return resultMessage
     */
    ResultMessage updateUserById(UserDTO user);
}
