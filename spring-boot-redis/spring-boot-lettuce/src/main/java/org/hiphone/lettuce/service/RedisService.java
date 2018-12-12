package org.hiphone.lettuce.service;

import org.hiphone.lettuce.entitys.ResultMessage;

public interface RedisService {

    ResultMessage setValueByKey(String key, String value);

    ResultMessage getValueByKey(String key);
}
