package org.hiphone.mybatis.service;

import org.hiphone.mybatis.model.ResultMessage;

public interface UserService {

    /**
     * 返回数据库中user_account表中所有的数据
     * @return resultMessage
     */
    ResultMessage listAllUserData();
}
