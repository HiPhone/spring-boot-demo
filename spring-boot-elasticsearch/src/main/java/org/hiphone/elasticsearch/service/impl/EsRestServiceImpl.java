package org.hiphone.elasticsearch.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.hiphone.elasticsearch.constants.Constant;
import org.hiphone.elasticsearch.entitys.ResultMessage;
import org.hiphone.elasticsearch.exception.ReturnMsg;
import org.hiphone.elasticsearch.service.EsRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author HiPhone
 */
@Service
public class EsRestServiceImpl implements EsRestService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${elasticsearch.rest-url}")
    private String elasticsearchUrl;

    @Override
    public ResultMessage createEsMapping(String indexName, JSONObject mappings) {
        String requestUrl = this.constructRequestUrl(indexName, null, null, null);
        return new ResultMessage(
                ReturnMsg.SUCCESS.getCode(),
                ReturnMsg.SUCCESS.getMessage(),
                restTemplate.exchange(requestUrl, HttpMethod.PUT, this.constructHttpEntity(mappings), JSONObject.class).getBody()
        );
    }

    @Override
    public ResultMessage deleteEsMapping(String indexName) {
        String requestUrl = this.constructRequestUrl(indexName, null, null, null);
        return this.deleteOperation(requestUrl);
    }

    @Override
    public ResultMessage addDataToIndex(String indexName, String type, Map<String, String> data) {
        String requestUrl = this.constructRequestUrl(indexName, type, null, null);
        return new ResultMessage(
                ReturnMsg.SUCCESS.getCode(),
                ReturnMsg.SUCCESS.getMessage(),
                restTemplate.exchange(requestUrl, HttpMethod.POST, this.constructHttpEntity(data), JSONObject.class).getBody()
        );
    }

    @Override
    public ResultMessage deleteDataFromIndex(String indexName, String type, String id) {
        String requestUrl = this.constructRequestUrl(indexName, type, id, null);
        return this.deleteOperation(requestUrl);
    }

    @Override
    public ResultMessage updateDataFromIndex(String indexName, String type, String id, Map<String, String> data) {
        String requestUrl = this.constructRequestUrl(indexName, type, id, Constant.ES_UPDATE_END);
        JSONObject updateData = new JSONObject();
        updateData.put("doc", data);
        return new ResultMessage(
                    ReturnMsg.SUCCESS.getCode(),
                    ReturnMsg.SUCCESS.getMessage(),
                    restTemplate.exchange(requestUrl, HttpMethod.POST, this.constructHttpEntity(updateData), JSONObject.class).getBody()
            );
    }

    /**
     * 发起delete请求
     * @param requestUrl 请求地址
     * @return 请求的结果
     */
    private ResultMessage deleteOperation(String requestUrl) {
        return new ResultMessage(
                ReturnMsg.SUCCESS.getCode(),
                ReturnMsg.SUCCESS.getMessage(),
                restTemplate.exchange(requestUrl, HttpMethod.DELETE, this.constructHttpEntity(null), JSONObject.class).getBody()
        );
    }


    /**
     * 构造请求的http entity
     * @param data 数据
     * @return 填充数据的http entity
     */
    private HttpEntity<?> constructHttpEntity(Object data) {
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON_UTF8);
        header.add("Accept", MediaType.APPLICATION_JSON.toString());
        //添加header修饰
        return new HttpEntity<>(data, header);
    }

    /**
     * 构造请求的url
     * @param indexName 索引名称
     * @param type 数据存放的type
     * @param id 数据的id
     * @param end 数据的结尾
     * @return 构造完成的url
     */
    private String constructRequestUrl(String indexName, String type, String id, String end) {
        String requestUrl = elasticsearchUrl + "/" + indexName;
        if (type != null) {
            requestUrl += "/" + type;
        }
        if (id != null) {
            requestUrl += "/" + id;
        }
        if (end != null) {
            requestUrl += "/" + end;
        }
        return requestUrl;
    }
}
