package org.hiphone.lettuce.controller;

import lombok.extern.slf4j.Slf4j;
import org.hiphone.lettuce.entitys.ResultMessage;
import org.hiphone.lettuce.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HiPhone
 */
@Slf4j
@RestController
public class RedisController {

    @Autowired
    private RedisService redisService;

    @PostMapping("/value")
    public ResultMessage setValueByKey(@RequestParam("key") String key,
                                       @RequestParam("value") String value) {
        return redisService.setValueByKey(key, value);
    }

    @GetMapping("/value")
    public ResultMessage getValueByKey(@RequestParam("key") String key) {
        return redisService.getValueByKey(key);
    }

}
