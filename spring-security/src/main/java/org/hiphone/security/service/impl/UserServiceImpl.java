package org.hiphone.security.service.impl;

import org.hiphone.security.constants.ReturnCode;
import org.hiphone.security.entitys.ResultMessage;
import org.hiphone.security.entitys.UserDTO;
import org.hiphone.security.mapper.UserMapper;
import org.hiphone.security.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author HiPhone
 */
@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResultMessage checkLoginUser(UserDTO loginInfo) {
        ResultMessage resultMessage;

        try {
            logger.info("Starting to check login info which loginName is {}", loginInfo.getLoginName());
            if (userMapper.getUserByLoginName(loginInfo.getLoginName()).getPassword().equals(loginInfo.getPassword())) {
                resultMessage = new ResultMessage(ReturnCode.SUCCESS.getCode(),
                        ReturnCode.LOGIN_SUCCESS.getMessage(),
                        null);
            } else {
                resultMessage = new ResultMessage(ReturnCode.SUCCESS.getCode(),
                        ReturnCode.LOGIN_FAIL.getMessage(),
                        null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("database get error, message is {}", e.getMessage());
            resultMessage = new ResultMessage(ReturnCode.SQL_ERROR.getCode(),
                    ReturnCode.SQL_ERROR.getMessage(),
                    e.getMessage());
        }
        return resultMessage;
    }
}
