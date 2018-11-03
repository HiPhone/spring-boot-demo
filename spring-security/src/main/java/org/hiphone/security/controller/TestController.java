package org.hiphone.security.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hiphone.security.constants.ReturnCode;
import org.hiphone.security.entitys.ResultMessage;
import org.hiphone.security.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HiPhone
 */
@RestController
@Api(value = "TestController", description = "用于测试服务是否存活的Controller")
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Value("${spring.application.name}")
    private String applicationName;

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
    @GetMapping("/")
    public ResultMessage home() {
        return new ResultMessage(
                ReturnCode.SUCCESS.getCode(),
                ReturnCode.SUCCESS.getMessage(),
                "interesting");
    }

    @ResponseBody
    @GetMapping("/admin")
    public ResultMessage adminPage() {
        return new ResultMessage(
                ReturnCode.SUCCESS.getCode(),
                ReturnCode.SUCCESS.getMessage(),
                "adminPage"
        );
    }

    @ResponseBody
    @GetMapping("/dba")
    public ResultMessage dbaPage() {
        return new ResultMessage(
                ReturnCode.SUCCESS.getCode(),
                ReturnCode.SUCCESS.getMessage(),
                "dbaPage"
        );
    }

    @ResponseBody
    @GetMapping("/user")
    public ResultMessage userPage() {
        return new ResultMessage(
                ReturnCode.SUCCESS.getCode(),
                ReturnCode.SUCCESS.getMessage(),
                "userPage"
        );
    }
}
