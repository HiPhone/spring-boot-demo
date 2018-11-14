package org.hiphone.elasticsearch.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.client.transport.TransportClient;
import org.hiphone.elasticsearch.constants.ReturnCode;
import org.hiphone.elasticsearch.entitys.ResultMessage;
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
        ResultMessage resultMessage;

        try {
            resultMessage = new ResultMessage(
                    ReturnCode.SUCCESS.getCode(),
                    ReturnCode.SUCCESS.getMessage(),
                    ESUtil.insert(transportClient, indexName, data)
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
    public ResultMessage getDataById(String indexName, String id) {
        ResultMessage resultMessage;

        try {
            resultMessage = new ResultMessage(
                    ReturnCode.SUCCESS.getCode(),
                    ReturnCode.SUCCESS.getMessage(),
                    ESUtil.getDataById(transportClient, indexName, id)
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
    public ResultMessage updateDataById(String indexName, String id, JSONObject data) {
        ResultMessage resultMessage;

        try {
            resultMessage = new ResultMessage(
                    ReturnCode.SUCCESS.getCode(),
                    ReturnCode.SUCCESS.getMessage(),
                    ESUtil.updateDataById(transportClient, indexName, id, data)
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
    public ResultMessage deleteDataById(String indexName, String id) {
        ResultMessage resultMessage;

        try {
            resultMessage = new ResultMessage(
                    ReturnCode.SUCCESS.getCode(),
                    ReturnCode.SUCCESS.getMessage(),
                    ESUtil.deleteDataById(transportClient, indexName, id)
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
}
