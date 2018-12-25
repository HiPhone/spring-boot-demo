package org.hiphone.mybatis.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.hiphone.mybatis.constants.ReturnMsg;
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
            resultMessage = new ResultMessage(ReturnMsg.SUCCESS.getCode(),
                    ReturnMsg.SUCCESS.getMessage(),
                    userMapper.listAllUserData());
            log.error("query all user data from database success");
        } catch (Exception e) {
            log.error("database get error, message is {}", e.getMessage());
            resultMessage = new ResultMessage(ReturnMsg.SQL_ERROR.getCode(),
                    ReturnMsg.SQL_ERROR.getMessage(),
                    e.getMessage());
        }

        return resultMessage;
    }

    @Override
    public ResultMessage getUserById(Long id) {
        ResultMessage resultMessage;

        try {
            resultMessage = new ResultMessage(ReturnMsg.SUCCESS.getCode(),
                    ReturnMsg.SUCCESS.getMessage(),
                    userMapper.getUserById(id));
            log.error("query user data by id from database success");
        } catch (Exception e) {
            log.error("database get error, message is {}", e.getMessage());
            resultMessage = new ResultMessage(ReturnMsg.SQL_ERROR.getCode(),
                    ReturnMsg.SQL_ERROR.getMessage(),
                    e.getMessage());
        }

        return resultMessage;
    }

    @Override
    public ResultMessage deleteUserById(Long id) {
        ResultMessage resultMessage;

        try {
            resultMessage = new ResultMessage(ReturnMsg.SUCCESS.getCode(),
                    ReturnMsg.SUCCESS.getMessage(),
                    userMapper.deleteUserById(id));
            log.error("delete user data by id from database success");
        } catch (Exception e) {
            log.error("database get error, message is {}", e.getMessage());
            resultMessage = new ResultMessage(ReturnMsg.DELETE_ERROR.getCode(),
                    ReturnMsg.DELETE_ERROR.getMessage(),
                    e.getMessage());
        }

        return resultMessage;
    }

    @Override
    public ResultMessage insertNewUser(UserDTO user) {
        ResultMessage resultMessage;

        try {
            resultMessage = new ResultMessage(ReturnMsg.SUCCESS.getCode(),
                    ReturnMsg.SUCCESS.getMessage(),
                    userMapper.insertNewUser(user));
            log.error("insert new user data into database success");
        } catch (Exception e) {
            log.error("insert user data get error, message is {}", e.getMessage());
            resultMessage = new ResultMessage(ReturnMsg.INSERT_ERROR.getCode(),
                    ReturnMsg.INSERT_ERROR.getMessage(),
                    e.getMessage());
        }

        return resultMessage;
    }

    @Override
    public ResultMessage updateUserById(UserDTO user) {
        ResultMessage resultMessage;
        user.setUpdateTime(new Date(System.currentTimeMillis()));

        try {
            resultMessage = new ResultMessage(ReturnMsg.SUCCESS.getCode(),
                    ReturnMsg.SUCCESS.getMessage(),
                    userMapper.updateUserById(user));
            log.error("update user data into database success");
        } catch (Exception e) {
            log.error("update user data get error, message is {}", e.getMessage());
            resultMessage = new ResultMessage(ReturnMsg.UPDATE_ERROR.getCode(),
                    ReturnMsg.UPDATE_ERROR.getMessage(),
                    e.getMessage());
        }

        return resultMessage;
    }
}
