package org.hiphone.elasticsearch.utils;

import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.hiphone.elasticsearch.constants.Constant;

/**
 * @author HiPhone
 */
public class ESUtil {

    /**
     * 使用transport client向es中插入数据
     * @param client transport client
     * @param indexName 索引名称
     * @param data 数据
     * @return 新增数据的id
     */
    public static String insert(TransportClient client, String indexName, JSONObject data) {
        IndexResponse response = client.prepareIndex(indexName, Constant.DEFAULT_ES_TYPE)
                .setSource(data, XContentType.JSON)
                .setRefreshPolicy(WriteRequest.RefreshPolicy.IMMEDIATE)
                .get();
        if (response.getResult() != DocWriteResponse.Result.CREATED) {
            throw new ElasticsearchException("failed to insert data into index: " + indexName);
        }
        return response.getId();
    }
}
