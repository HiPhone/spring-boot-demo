package org.hiphone.security.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.hiphone.security.exception.ReturnMsg;
import org.hiphone.security.entitys.ResultMessage;
import org.hiphone.security.entitys.UserDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author HiPhone
 */
@Slf4j
@RestController
@Api(value = "UserController", description = "用户操作的Controller")
public class UserController {

    @ResponseBody
    @GetMapping("/")
    @ApiOperation(value = "登陆成功的接口", notes = "返回登陆成功的信息")
    public ResultMessage home(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user != null) {
            log.info("User {} login success!", user.getLoginName());
        }
        return new ResultMessage(
                ReturnMsg.SUCCESS.getCode(),
                ReturnMsg.SUCCESS.getMessage(),
                System.currentTimeMillis());
    }

    @ResponseBody
    @GetMapping("/login")
    @ApiOperation(value = "登录信息验证失败返回接口", notes = "返回错误信息")
    public ResultMessage loginError() {
        log.info("login failed, returning error message");
        return new ResultMessage(
                ReturnMsg.LOGIN_FAIL.getCode(),
                ReturnMsg.LOGIN_FAIL.getMessage(),
                System.currentTimeMillis()
        );
    }

    @ResponseBody
    @PostMapping("/")
    @ApiOperation(value = "登陆成功的接口", notes = "返回登陆成功需要跳转的url")
    public ResultMessage loginSuccess(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user != null) {
            log.info("User {} login success!", user.getLoginName());
        }
        return new ResultMessage(
                ReturnMsg.LOGIN_SUCCESS.getCode(),
                ReturnMsg.LOGIN_SUCCESS.getMessage(),
                "/"
        );
    }

    @ResponseBody
    @GetMapping("/admin")
    @ApiOperation(value = "只有角色为ADMIN能访问的接口", notes = "返回ADMIN的信息")
    public ResultMessage adminPage(HttpServletRequest request) {

        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");
        log.info("Admin {} request the adminPage", user.getLoginName());

        return new ResultMessage(
                ReturnMsg.SUCCESS.getCode(),
                ReturnMsg.SUCCESS.getMessage(),
                "欢迎您，系统管理员"
        );
    }

    @ResponseBody
    @GetMapping("/dba")
    @ApiOperation(value = "只有角色为DBA能访问的接口", notes = "返回DBA的信息")
    public ResultMessage dbaPage(HttpServletRequest request) {

        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user != null) {
            log.info("DBA {} request the dbaPage", user.getLoginName());
        }

        return new ResultMessage(
                ReturnMsg.SUCCESS.getCode(),
                ReturnMsg.SUCCESS.getMessage(),
                "欢迎您，数据库管理员"
        );
    }

    @ResponseBody
    @GetMapping("/user")
    @ApiOperation(value = "只有角色为USER能访问的接口", notes = "返回USER的信息")
    public ResultMessage userPage(HttpServletRequest request) {

        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user != null) {
            log.info("USER {} request the userPage", user.getLoginName());
        }

        return new ResultMessage(
                ReturnMsg.SUCCESS.getCode(),
                ReturnMsg.SUCCESS.getMessage(),
                "欢迎您，用户"
        );
    }
}
