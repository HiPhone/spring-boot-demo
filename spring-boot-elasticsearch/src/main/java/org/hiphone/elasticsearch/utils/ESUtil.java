package org.hiphone.elasticsearch.utils;

import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.hiphone.elasticsearch.constants.Constant;
import org.hiphone.elasticsearch.entitys.SearchResult;

import java.util.Map;


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

    /**
     * 通过id更新es中对应的数据
     * @param client transport client
     * @param indexName 索引名称
     * @param id 数据的id
     * @param data 数据
     * @return 更新的结果
     */
    public static String updateDataById(TransportClient client, String indexName, String id, JSONObject data) {
        UpdateResponse response = client.prepareUpdate(indexName, Constant.DEFAULT_ES_TYPE, id)
                .setDoc(data, XContentType.JSON)
                .setRefreshPolicy(WriteRequest.RefreshPolicy.IMMEDIATE)
                .setFetchSource(false)
                .setDetectNoop(false)
                .get();
        return response.getResult().toString();
    }

    /**
     * 通过id查询对应的数据
     * @param client transport client
     * @param indexName 索引名称
     * @param id 数据id
     * @return id对应的单条数据
     */
    public static SearchResult getDataById(TransportClient client, String indexName, String id) {
        GetResponse response = client.prepareGet(indexName, Constant.DEFAULT_ES_TYPE, id)
                .setFetchSource(true)
                .execute()
                .actionGet();
        if (!response.isExists()) {
            return SearchResult.EMPTY_RESULT;
        }
        SearchResult result = new SearchResult(1);
        Map<String, Object> source = response.getSourceAsMap();
        source.put("id", response.getId());
        result.add(response.getSourceAsMap(), null);
        return result;
    }

    /**
     * 通过id删除es中的数据
     * @param client transport client
     * @param indexName 索引名称
     * @param id 要删除的数据的id
     * @return resultMessage
     */
    public static String deleteDataById(TransportClient client, String indexName, String id) {
        DeleteResponse response = client.prepareDelete(indexName, Constant.DEFAULT_ES_TYPE, id)
                .execute()
                .actionGet();
        return response.getResult().toString();
    }
}
