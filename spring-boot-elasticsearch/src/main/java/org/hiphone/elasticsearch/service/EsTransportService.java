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

    /**
     * 通过数据的id从es对饮的索引中获取单条数据
     * @param indexName 索引名
     * @param id 数据id
     * @return resultMessage
     */
    ResultMessage getDataById(String indexName, String id);

    /**
     * 通过数据id更新数据
     * @param indexName 索引的名称
     * @param id 数据id
     * @param data 新数据
     * @return resultMessage
     */
    ResultMessage updateDataById(String indexName, String id, JSONObject data);

    /**
     * 通过数据的id删除es中对应的数据
     * @param indexName 索引的名称
     * @param id 数据的id
     * @return resultMessage
     */
    ResultMessage deleteDataById(String indexName, String id);
}
