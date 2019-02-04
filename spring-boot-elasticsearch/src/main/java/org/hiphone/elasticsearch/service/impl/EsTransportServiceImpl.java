package org.hiphone.elasticsearch.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.client.transport.TransportClient;
import org.hiphone.elasticsearch.entitys.ResultMessage;
import org.hiphone.elasticsearch.exception.ReturnMsg;
import org.hiphone.elasticsearch.service.EsTransportService;
import org.hiphone.elasticsearch.utils.ESUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author HiPhone
 */
@Service
public class EsTransportServiceImpl implements EsTransportService {

    @Autowired
    private TransportClient transportClient;

    @Override
    public ResultMessage insertDataToEsByTransport(String indexName, JSONObject data) {
        return new ResultMessage(
                ReturnMsg.SUCCESS.getCode(),
                ReturnMsg.SUCCESS.getMessage(),
                ESUtil.insert(transportClient, indexName, data)
        );
    }

    @Override
    public ResultMessage getDataById(String indexName, String id) {
        return new ResultMessage(
                ReturnMsg.SUCCESS.getCode(),
                ReturnMsg.SUCCESS.getMessage(),
                ESUtil.getDataById(transportClient, indexName, id)
        );
    }

    @Override
    public ResultMessage updateDataById(String indexName, String id, JSONObject data) {

        return new ResultMessage(
                ReturnMsg.SUCCESS.getCode(),
                ReturnMsg.SUCCESS.getMessage(),
                ESUtil.updateDataById(transportClient, indexName, id, data)
        );
    }

    @Override
    public ResultMessage deleteDataById(String indexName, String id) {
        return new ResultMessage(
                ReturnMsg.SUCCESS.getCode(),
                ReturnMsg.SUCCESS.getMessage(),
                ESUtil.deleteDataById(transportClient, indexName, id)
        );

    }
}
