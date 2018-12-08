package org.hiphone.redis.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.hiphone.redis.entitys.ResultMessage;
import org.hiphone.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author HiPhone
 */
@Slf4j
@RestController
@Api(value = "RedisController", description = "操作redis的controller")
public class RedisController {

    @Autowired
    private RedisService redisService;

    @ResponseBody
    @PostMapping("/string")
    @ApiOperation(value = "设置String数据结构的值", notes = "返回操作结果")
    public ResultMessage setString(@RequestParam(name = "key") @ApiParam(name = "key", value = "key的名称") String key,
                                   @RequestParam(name = "value") @ApiParam(name = "value", value = "key对应的值") String value) {
        return redisService.setStringByKey(key, value);
    }

    @ResponseBody
    @GetMapping("/string")
    @ApiOperation(value = "根据key获取redis中的value", notes = "返回数据")
    public ResultMessage getValue(@RequestParam(name = "key") @ApiParam(name = "key", value = "key的名称") String key) {
        return redisService.getStringByKey(key);
    }
}
