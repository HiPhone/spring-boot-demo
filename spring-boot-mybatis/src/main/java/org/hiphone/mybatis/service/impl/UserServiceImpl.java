package org.hiphone.mybatis.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.hiphone.mybatis.constants.ReturnCode;
import org.hiphone.mybatis.entitys.UserDTO;
import org.hiphone.mybatis.mapper.UserMapper;
import org.hiphone.mybatis.model.ResultMessage;
import org.hiphone.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author HiPhone
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResultMessage listAllUserData() {
        ResultMessage resultMessage;

        try {
            resultMessage = new ResultMessage(ReturnCode.SUCCESS.getCode(),
                    ReturnCode.SUCCESS.getMessage(),
                    userMapper.listAllUserData());
            log.error("query all user data from database success");
        } catch (Exception e) {
            log.error("database get error, message is {}", e.getMessage());
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
            log.error("query user data by id from database success");
        } catch (Exception e) {
            log.error("database get error, message is {}", e.getMessage());
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
            log.error("delete user data by id from database success");
        } catch (Exception e) {
            log.error("database get error, message is {}", e.getMessage());
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
            log.error("insert new user data into database success");
        } catch (Exception e) {
            log.error("insert user data get error, message is {}", e.getMessage());
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
            log.error("update user data into database success");
        } catch (Exception e) {
            log.error("update user data get error, message is {}", e.getMessage());
            resultMessage = new ResultMessage(ReturnCode.UPDATE_ERROR.getCode(),
                    ReturnCode.UPDATE_ERROR.getMessage(),
                    e.getMessage());
        }

        return resultMessage;
    }
}
