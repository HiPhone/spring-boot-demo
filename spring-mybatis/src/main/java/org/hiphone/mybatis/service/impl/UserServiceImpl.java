package org.hiphone.mybatis.service.impl;

import org.hiphone.mybatis.constants.ReturnCode;
import org.hiphone.mybatis.mapper.UserMapper;
import org.hiphone.mybatis.model.ResultMessage;
import org.hiphone.mybatis.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResultMessage listAllUserData() {
        ResultMessage resultMessage = null;

        try {
            resultMessage = new ResultMessage(ReturnCode.SUCCESS.getCode(),
                    ReturnCode.SUCCESS.getMessage(),
                    userMapper.listAllUserData());
        } catch (Exception e) {
            logger.error("database get error, message is {}", e.getMessage());
            resultMessage = new ResultMessage(ReturnCode.SQL_ERROR.getCode(),
                    ReturnCode.SQL_ERROR.getMessage(),
                    e.getMessage());
        }

        return resultMessage;
    }
}
