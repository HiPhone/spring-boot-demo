package org.hiphone.redis.service.impl;

import org.hiphone.redis.constants.ReturnCode;
import org.hiphone.redis.entitys.ResultMessage;
import org.hiphone.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

/**
 * @author HiPhone
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private Jedis jedis;


    @Override
    public ResultMessage getStringByKey(String key) {
        return new ResultMessage(ReturnCode.SUCCESS.getCode(),
                ReturnCode.SUCCESS.getMessage(),
                jedis.get(key));
    }

    @Override
    public ResultMessage setStringByKey(String key, String value) {
        return new ResultMessage(ReturnCode.SUCCESS.getCode(),
                ReturnCode.SUCCESS.getMessage(),
                jedis.set(key, value));
    }
}
