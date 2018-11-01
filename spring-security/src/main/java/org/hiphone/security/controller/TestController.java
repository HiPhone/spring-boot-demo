package org.hiphone.security.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hiphone.security.constants.ReturnCode;
import org.hiphone.security.entitys.ResultMessage;
import org.hiphone.security.entitys.UserDTO;
import org.hiphone.security.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author HiPhone
 */
@RestController
@Api(value = "TestController", description = "用于测试服务是否存活的Controller")
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Value("${spring.application.name}")
    private String applicationName;

    @Autowired
    private UserService userService;

    @ResponseBody
    @GetMapping("/echo-test")
    @ApiOperation(value = "用于确认服务是否存活的接口", notes = "返回自身状态")
    public ResultMessage test() {
        logger.info("Receive a request for testing " + applicationName);

        return new ResultMessage(
                ReturnCode.SUCCESS.getCode(),
                ReturnCode.SUCCESS.getMessage(),
                applicationName + " is alive");
    }

    @ResponseBody
    @PostMapping("/echo-test")
    public ResultMessage test2(@RequestBody UserDTO userInfo) {
        return userService.checkLoginUser(userInfo);
    }

    @ResponseBody
    @GetMapping("/")
    public ResultMessage home(HttpServletRequest request, HttpServletResponse response) {
        CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
        response.setHeader("X-CSRF-HEADER", token.getHeaderName());
        response.setHeader("X-CSRF-PARAM", token.getParameterName());
        response.setHeader("X-CSRF-TOKEN", token.getToken());
        return new ResultMessage(
                ReturnCode.SUCCESS.getCode(),
                ReturnCode.SUCCESS.getMessage(),
                "interesting");
    }
}
