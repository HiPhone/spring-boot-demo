package org.hiphone.security.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hiphone.security.constants.ReturnCode;
import org.hiphone.security.entitys.ResultMessage;
import org.hiphone.security.entitys.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author HiPhone
 */
@RestController
@Api(value = "UserController", description = "用户操作的Controller")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @ResponseBody
    @GetMapping("/")
    @ApiOperation(value = "登陆成功的接口", notes = "返回登陆成功的信息")
    public ResultMessage home(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user != null) {
            logger.info("User {} login success!", user.getLoginName());
        }
        return new ResultMessage(
                ReturnCode.SUCCESS.getCode(),
                ReturnCode.SUCCESS.getMessage(),
                "登陆成功");
    }

    @ResponseBody
    @GetMapping("/admin")
    @ApiOperation(value = "只有角色为ADMIN能访问的接口", notes = "返回ADMIN的信息")
    public ResultMessage adminPage(HttpServletRequest request) {

        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");
        logger.info("Admin {} request the adminPage", user.getLoginName());

        return new ResultMessage(
                ReturnCode.SUCCESS.getCode(),
                ReturnCode.SUCCESS.getMessage(),
                "adminPage"
        );
    }

    @ResponseBody
    @GetMapping("/dba")
    @ApiOperation(value = "只有角色为DBA能访问的接口", notes = "返回DBA的信息")
    public ResultMessage dbaPage(HttpServletRequest request) {

        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user != null) {
            logger.info("DBA {} request the dbaPage", user.getLoginName());
        }

        return new ResultMessage(
                ReturnCode.SUCCESS.getCode(),
                ReturnCode.SUCCESS.getMessage(),
                "dbaPage"
        );
    }

    @ResponseBody
    @GetMapping("/user")
    @ApiOperation(value = "只有角色为USER能访问的接口", notes = "返回USER的信息")
    public ResultMessage userPage(HttpServletRequest request) {

        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user != null) {
            logger.info("USER {} request the userPage", user.getLoginName());
        }

        return new ResultMessage(
                ReturnCode.SUCCESS.getCode(),
                ReturnCode.SUCCESS.getMessage(),
                "userPage"
        );
    }
}
