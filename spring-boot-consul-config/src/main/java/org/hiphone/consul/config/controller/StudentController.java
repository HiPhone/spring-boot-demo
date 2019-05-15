package org.hiphone.consul.config.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hiphone.consul.config.config.StudentConfig;
import org.hiphone.consul.config.constants.ReturnMsg;
import org.hiphone.consul.config.entitys.ResultMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author HiPhone
 */
@RestController
@RequestMapping("/student")
@Api(value = "StudentController", tags = "用于测试consul配置的controller")
public class StudentController {

    /**
     * 使用@Value导入的数据无法使用consul的修改实时更新
     */
    @Value("${myName}")
    private String myName;

    @Resource
    private StudentConfig studentConfig;

    @GetMapping("/my-name")
    @ApiOperation(value = "用于获取单个myName", notes = "返回myName的值")
    public ResultMessage getMyName() {
        return new ResultMessage(ReturnMsg.SUCCESS.getCode(),
                ReturnMsg.SUCCESS.getMessage(),
                myName);
    }

    @GetMapping("/properties")
    @ApiOperation(value = "用于返回student信息", notes = "返回student的配置，可以通过consul修改刷新")
    public ResultMessage getConfigs() {
        return new ResultMessage(ReturnMsg.SUCCESS.getCode(),
                ReturnMsg.SUCCESS.getMessage(),
                studentConfig);
    }
}
