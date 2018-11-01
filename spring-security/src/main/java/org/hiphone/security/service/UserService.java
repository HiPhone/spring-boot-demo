package org.hiphone.security.service;

import org.hiphone.security.entitys.ResultMessage;
import org.hiphone.security.entitys.UserDTO;

/**
 * @author HiPhone
 */
public interface UserService {

    /**
     * 通过登陆名获取登陆该用户的密码
     * @param loginInfo 登陆名
     * @return resultMessage
     */
    ResultMessage checkLoginUser(UserDTO loginInfo);
}
