package org.hiphone.security.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.hiphone.security.constants.ReturnCode;
import org.hiphone.security.entitys.ResultMessage;
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
        ResultMessage resultMessage;

        try {
            log.info("Starting to check login info which loginName is {}", loginName);

            resultMessage = new ResultMessage(ReturnCode.SUCCESS.getCode(),
                    ReturnCode.LOGIN_SUCCESS.getMessage(),
                    userMapper.getUserByLoginName(loginName));

        } catch (Exception e) {
            e.printStackTrace();
            log.error("database get error, message is {}", e.getMessage());
            resultMessage = new ResultMessage(ReturnCode.SQL_ERROR.getCode(),
                    ReturnCode.SQL_ERROR.getMessage(),
                    e.getMessage());
        }
        return resultMessage;
    }
}
