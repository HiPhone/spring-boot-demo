package org.hiphone.elasticsearch.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.hiphone.elasticsearch.constants.Constant;
import org.hiphone.elasticsearch.constants.ReturnCode;
import org.hiphone.elasticsearch.entitys.ResultMessage;
import org.hiphone.elasticsearch.service.EsRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
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
        ResultMessage resultMessage;
        try {
            JSONObject result = restTemplate.exchange(requestUrl, HttpMethod.PUT, this.constructHttpEntity(mappings), JSONObject.class).getBody();
            resultMessage = new ResultMessage(
                    ReturnCode.SUCCESS.getCode(),
                    ReturnCode.SUCCESS.getMessage(),
                    result
            );
        } catch (Exception e) {
            if (Constant.BAD_REQUEST_MSG.equals(e.getMessage())) {
                resultMessage = new ResultMessage(
                        ReturnCode.INDEX_EXISTS.getCode(),
                        ReturnCode.INDEX_EXISTS.getMessage(),
                        e.getMessage()
                );
            } else {
                resultMessage = new ResultMessage(
                        ReturnCode.UNKNOWN_ERROR.getCode(),
                        ReturnCode.UNKNOWN_ERROR.getMessage(),
                        e.getMessage()
                );
            }

        }

        return resultMessage;
    }

    @Override
    public ResultMessage deleteEsMapping(String indexName) {
        String requestUrl = this.constructRequestUrl(indexName, null, null, null);
        return this.deleteOperation(requestUrl);
    }

    @Override
    public ResultMessage addDataToIndex(String indexName, String type, Map<String, String> data) {
        String requestUrl = this.constructRequestUrl(indexName, null, null, null);
        ResultMessage resultMessage;

        try {
            JSONObject result = restTemplate.exchange(requestUrl, HttpMethod.PUT, this.constructHttpEntity(data), JSONObject.class).getBody();
            resultMessage = new ResultMessage(
                    ReturnCode.SUCCESS.getCode(),
                    ReturnCode.SUCCESS.getMessage(),
                    result
            );
        } catch (Exception e) {
            resultMessage = new ResultMessage(
                    ReturnCode.UNKNOWN_ERROR.getCode(),
                    ReturnCode.UNKNOWN_ERROR.getMessage(),
                    e.getMessage()
            );
        }
        return resultMessage;
    }

    @Override
    public ResultMessage deleteDataFromIndex(String indexName, String type, String id) {
        String requestUrl = this.constructRequestUrl(indexName, type, id, null);

        return this.deleteOperation(requestUrl);
    }

    @Override
    public ResultMessage updateDataFromIndex(String indexName, String type, String id, Map<String, String> data) {
        String requestUrl = this.constructRequestUrl(indexName, type, id, Constant.ES_UPDATE_END);
        ResultMessage resultMessage;
        JSONObject updateData = new JSONObject();
        updateData.put("doc", data);
        try {
            JSONObject result = restTemplate.exchange(requestUrl, HttpMethod.POST, this.constructHttpEntity(updateData), JSONObject.class).getBody();
            resultMessage = new ResultMessage(
                    ReturnCode.SUCCESS.getCode(),
                    ReturnCode.SUCCESS.getMessage(),
                    result
            );
        } catch (Exception e) {
            resultMessage = new ResultMessage(
                    ReturnCode.UNKNOWN_ERROR.getCode(),
                    ReturnCode.UNKNOWN_ERROR.getMessage(),
                    e.getMessage()
            );
        }
        return resultMessage;
    }

    private ResultMessage deleteOperation(String requestUrl) {
        ResultMessage resultMessage;
        try {
            JSONObject result = restTemplate.exchange(requestUrl, HttpMethod.DELETE, this.constructHttpEntity(null), JSONObject.class).getBody();
            resultMessage = new ResultMessage(
                    ReturnCode.SUCCESS.getCode(),
                    ReturnCode.SUCCESS.getMessage(),
                    result
            );
        } catch (Exception e) {
            if (Constant.NOT_FOUND.equals(e.getMessage())) {
                resultMessage = new ResultMessage(
                        ReturnCode.INDEX_NOT_FOUND.getCode(),
                        ReturnCode.INDEX_NOT_FOUND.getMessage(),
                        e.getMessage()
                );
            } else {
                resultMessage = new ResultMessage(
                        ReturnCode.UNKNOWN_ERROR.getCode(),
                        ReturnCode.UNKNOWN_ERROR.getMessage(),
                        e.getMessage()
                );
            }
        }
        return resultMessage;
    }


    private HttpEntity<?> constructHttpEntity(Object data) {
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON_UTF8);
        header.add("Accept", MediaType.APPLICATION_JSON.toString());
        //添加header修饰
        return new HttpEntity<>(data, header);
    }

    private String constructRequestUrl(String indexName, String type, String id, String end) {
        String requestUrl = elasticsearchUrl + "/" + indexName;
        if (!StringUtils.isEmpty(type)) {
            requestUrl += "/" + type;
        }
        if (!StringUtils.isEmpty(id)) {
            requestUrl += "/" + id;
        }
        if (!StringUtils.isEmpty(end)) {
            requestUrl += "/" + end;
        }
        return requestUrl;
    }
}
