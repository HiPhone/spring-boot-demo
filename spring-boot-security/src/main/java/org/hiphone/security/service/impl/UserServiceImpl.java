package org.hiphone.security.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.hiphone.security.entitys.ResultMessage;
import org.hiphone.security.exception.ReturnMsg;
import org.hiphone.security.mapper.UserMapper;
import org.hiphone.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author HiPhone
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResultMessage getUserByLoginName(String loginName) {
        log.info("Starting to check login info which loginName is {}", loginName);
        return new ResultMessage(
                ReturnMsg.SUCCESS.getCode(),
                ReturnMsg.LOGIN_SUCCESS.getMessage(),
                userMapper.getUserByLoginName(loginName)
        );
    }
}
