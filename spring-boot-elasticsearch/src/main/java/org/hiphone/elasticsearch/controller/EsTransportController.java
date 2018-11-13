package org.hiphone.elasticsearch.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.hiphone.elasticsearch.entitys.ResultMessage;
import org.hiphone.elasticsearch.service.EsTransportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author HiPhone
 */
@RestController
@RequestMapping(value = "/transport")
@Api(value = "EsTransportController", description = "使用Es transport client操作Es")
public class EsTransportController {

    private static final Logger logger = LoggerFactory.getLogger(EsTransportController.class);

    @Autowired
    private EsTransportService esTransportService;

    @ResponseBody
    @PostMapping("/data/insert")
    @ApiOperation(value = "添加es的索引和类型的接口", notes = "发起PUT请求增加索引和类型")
    public ResultMessage insertDataByTransport(@RequestParam(name = "indexName") @ApiParam(name = "indexName", value = "索引名称") String indexName,
                                               @RequestBody @ApiParam(name = "data", value = "需要插入到es的数据") JSONObject data) {
        logger.info("Attempting to insert data to index: {}, data is {}", indexName, data);
        return esTransportService.insertDataToEsByTransport(indexName, data);
    }
}
