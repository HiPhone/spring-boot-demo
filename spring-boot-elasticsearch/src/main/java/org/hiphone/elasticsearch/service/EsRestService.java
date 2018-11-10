package org.hiphone.elasticsearch.service;

import com.alibaba.fastjson.JSONObject;
import org.hiphone.elasticsearch.entitys.ResultMessage;

/**
 * 封装rest操作es的service
 * @author HiPhone
 */
public interface EsRestService {

    /**
     * 动态创建es索引的方法
     * @param indexName 索引的名称
     * @param mappings 索引属性
     * @return resultMessage
     */
    ResultMessage createEsMapping(String indexName, JSONObject mappings);

    /**
     * 通过索引名称删除es索引
     * @param indexName 索引名称
     * @return resultMessage
     */
    ResultMessage deleteEsMapping(String indexName);

    /**
     * 向索引添加数据
     * @param indexName 索引名称
     * @param type 类型
     * @param data 数据
     * @return resultMessage
     */
    ResultMessage addDataToIndex(String indexName, String type, JSONObject data);

    /**
     * 从索引中删除指定id的数据
     * @param indexName 索引名
     * @param type 类型
     * @param id 数据的id
     * @return resultMessage
     */
    ResultMessage deleteDataFromIndex(String indexName, String type, String id);
}
