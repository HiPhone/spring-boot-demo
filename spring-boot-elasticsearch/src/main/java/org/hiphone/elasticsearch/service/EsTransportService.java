package org.hiphone.elasticsearch.service;

import com.alibaba.fastjson.JSONObject;
import org.hiphone.elasticsearch.entitys.ResultMessage;

/**
 * @author HiPhone
 */
public interface EsTransportService {

    /**
     * 插入数据data到索引名为indexName索引中
     * @param indexName 索引名称
     * @param data json格式的数据
     * @return resultMessage
     */
    ResultMessage insertDataToEsByTransport(String indexName, JSONObject data);
}
