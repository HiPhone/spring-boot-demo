package org.hiphone.redis.service;

import org.hiphone.redis.entitys.ResultMessage;

/**
 * @author HiPhone
 */
public interface RedisService {

    /**
     * 通过key获取redis中对应的string 类型
     * @param key redis中的key
     * @return key对应的值
     */
    ResultMessage getStringByKey(String key);

    /**
     * 存放数据到redis中
     * @param key redis的key
     * @param value key对应的value
     * @return resultMessage
     */
    ResultMessage setStringByKey(String key, String value);
}
