package org.hiphone.lettuce.service.impl;

import org.hiphone.lettuce.constants.ReturnCode;
import org.hiphone.lettuce.entitys.ResultMessage;
import org.hiphone.lettuce.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    @Override
    public ResultMessage setValueByKey(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
        return new ResultMessage(ReturnCode.SUCCESS.getCode(),
                ReturnCode.SUCCESS.getMessage(),
                null);
    }

    @Override
    public ResultMessage getValueByKey(String key) {
        return new ResultMessage(ReturnCode.SUCCESS.getCode(),
                ReturnCode.SUCCESS.getMessage(),
                redisTemplate.opsForValue().get(key)
        );
    }
}
