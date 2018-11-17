package org.hiphone.mybatis.service.impl;

import org.hiphone.mybatis.constants.ReturnCode;
import org.hiphone.mybatis.entitys.UserDTO;
import org.hiphone.mybatis.mapper.UserMapper;
import org.hiphone.mybatis.model.ResultMessage;
import org.hiphone.mybatis.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResultMessage listAllUserData() {
        ResultMessage resultMessage;

        try {
            resultMessage = new ResultMessage(ReturnCode.SUCCESS.getCode(),
                    ReturnCode.SUCCESS.getMessage(),
                    userMapper.listAllUserData());
            logger.error("query all user data from database success");
        } catch (Exception e) {
            logger.error("database get error, message is {}", e.getMessage());
            resultMessage = new ResultMessage(ReturnCode.SQL_ERROR.getCode(),
                    ReturnCode.SQL_ERROR.getMessage(),
                    e.getMessage());
        }

        return resultMessage;
    }

    @Override
    public ResultMessage getUserById(Long id) {
        ResultMessage resultMessage;

        try {
            resultMessage = new ResultMessage(ReturnCode.SUCCESS.getCode(),
                    ReturnCode.SUCCESS.getMessage(),
                    userMapper.getUserById(id));
            logger.error("query user data by id from database success");
        } catch (Exception e) {
            logger.error("database get error, message is {}", e.getMessage());
            resultMessage = new ResultMessage(ReturnCode.SQL_ERROR.getCode(),
                    ReturnCode.SQL_ERROR.getMessage(),
                    e.getMessage());
        }

        return resultMessage;
    }

    @Override
    public ResultMessage deleteUserById(Long id) {
        ResultMessage resultMessage;

        try {
            resultMessage = new ResultMessage(ReturnCode.SUCCESS.getCode(),
                    ReturnCode.SUCCESS.getMessage(),
                    userMapper.deleteUserById(id));
            logger.error("delete user data by id from database success");
        } catch (Exception e) {
            logger.error("database get error, message is {}", e.getMessage());
            resultMessage = new ResultMessage(ReturnCode.DELETE_ERROR.getCode(),
                    ReturnCode.DELETE_ERROR.getMessage(),
                    e.getMessage());
        }

        return resultMessage;
    }

    @Override
    public ResultMessage insertNewUser(UserDTO user) {
        ResultMessage resultMessage;

        try {
            resultMessage = new ResultMessage(ReturnCode.SUCCESS.getCode(),
                    ReturnCode.SUCCESS.getMessage(),
                    userMapper.insertNewUser(user));
            logger.error("insert new user data into database success");
        } catch (Exception e) {
            logger.error("insert user data get error, message is {}", e.getMessage());
            resultMessage = new ResultMessage(ReturnCode.INSERT_ERROR.getCode(),
                    ReturnCode.INSERT_ERROR.getMessage(),
                    e.getMessage());
        }

        return resultMessage;
    }

    @Override
    public ResultMessage updateUserById(UserDTO user) {
        ResultMessage resultMessage;
        user.setUpdateTime(new Date(System.currentTimeMillis()));

        try {
            resultMessage = new ResultMessage(ReturnCode.SUCCESS.getCode(),
                    ReturnCode.SUCCESS.getMessage(),
                    userMapper.updateUserById(user));
            logger.error("update user data into database success");
        } catch (Exception e) {
            logger.error("update user data get error, message is {}", e.getMessage());
            resultMessage = new ResultMessage(ReturnCode.UPDATE_ERROR.getCode(),
                    ReturnCode.UPDATE_ERROR.getMessage(),
                    e.getMessage());
        }

        return resultMessage;
    }
}
