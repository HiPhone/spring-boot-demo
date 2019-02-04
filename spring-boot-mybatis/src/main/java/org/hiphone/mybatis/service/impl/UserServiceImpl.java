package org.hiphone.mybatis.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.hiphone.mybatis.entitys.UserDTO;
import org.hiphone.mybatis.exception.ReturnMsg;
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
        ResultMessage resultMessage = new ResultMessage(
                ReturnMsg.SUCCESS.getCode(),
                ReturnMsg.SUCCESS.getMessage(),
                userMapper.listAllUserData()
        );
        log.info("query all user data from database success");
        return resultMessage;
    }

    @Override
    public ResultMessage getUserById(Long id) {
        ResultMessage resultMessage = new ResultMessage(
                ReturnMsg.SUCCESS.getCode(),
                ReturnMsg.SUCCESS.getMessage(),
                userMapper.getUserById(id)
        );
        log.info("query user data by id from database success");
        return resultMessage;
    }

    @Override
    public ResultMessage deleteUserById(Long id) {
        ResultMessage resultMessage = new ResultMessage(
                ReturnMsg.SUCCESS.getCode(),
                ReturnMsg.SUCCESS.getMessage(),
                userMapper.deleteUserById(id)
        );
        log.info("delete user data by id from database success");
        return resultMessage;
    }

    @Override
    public ResultMessage insertNewUser(UserDTO user) {
        ResultMessage resultMessage = new ResultMessage(
                ReturnMsg.SUCCESS.getCode(),
                ReturnMsg.SUCCESS.getMessage(),
                userMapper.insertNewUser(user)
        );
        log.info("insert new user data into database success");
        return resultMessage;
    }

    @Override
    public ResultMessage updateUserById(UserDTO user) {

        user.setUpdateTime(new Date(System.currentTimeMillis()));
        ResultMessage resultMessage = new ResultMessage(
                ReturnMsg.SUCCESS.getCode(),
                ReturnMsg.SUCCESS.getMessage(),
                userMapper.updateUserById(user)
        );
        log.info("update user data into database success");
        return resultMessage;
    }
}
