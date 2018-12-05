package org.hiphone.elasticsearch.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.hiphone.elasticsearch.entitys.ResultMessage;
import org.hiphone.elasticsearch.service.EsTransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author HiPhone
 */
@Slf4j
@RestController
@RequestMapping(value = "/transport/data")
@Api(value = "EsTransportController", description = "使用Es transport client操作Es")
public class EsTransportController {

    @Autowired
    private EsTransportService esTransportService;

    @ResponseBody
    @PostMapping("/add")
    @ApiOperation(value = "添加es的索引和类型的接口", notes = "通过transport client添加数据")
    public ResultMessage insertDataByTransport(@RequestParam(name = "indexName") @ApiParam(name = "indexName", value = "索引名称") String indexName,
                                               @RequestBody @ApiParam(name = "data", value = "需要插入到es的数据") JSONObject data) {
        log.info("Attempting to insert data to index: {}, data is {}", indexName, data);
        return esTransportService.insertDataToEsByTransport(indexName, data);
    }

    @ResponseBody
    @GetMapping("/query")
    @ApiOperation(value = "在es对应的索引中查询指定数据", notes = "通过id查询指定数据")
    public ResultMessage queryDataById(@RequestParam(name = "indexName") @ApiParam(name = "indexName", value = "索引名称") String indexName,
                                       @RequestParam(name = "id") @ApiParam(name = "id", value = "数据对应的id") String id) {
        log.info("Getting data by from index: {}, data's id is: {}", indexName, id);
        return esTransportService.getDataById(indexName, id);
    }

    @ResponseBody
    @PutMapping("/update")
    @ApiOperation(value = "更新es中的指定数据", notes = "通过id更新指定索引中的指定数据")
    public ResultMessage updateDataById(@RequestParam(name = "indexName") @ApiParam(name = "indexName", value = "索引名称") String indexName,
                                        @RequestParam(name = "id") @ApiParam(name = "id", value = "数据对应的id") String id,
                                        @RequestBody @ApiParam(name = "data", value = "新的数据") JSONObject data) {
        log.info("updating es's data, index: {}, id: {}, new data is {}", indexName, id, data);
        return esTransportService.updateDataById(indexName, id, data);
    }

    @ResponseBody
    @DeleteMapping("/delete")
    @ApiOperation(value = "删除es中的指定数据", notes = "通过id删除索引中指定的数据")
    public ResultMessage deleteDataById(@RequestParam(name = "indexName") @ApiParam(name = "indexName", value = "索引名称") String indexName,
                                        @RequestParam(name = "id") @ApiParam(name = "id", value = "数据对应的id") String id) {
        log.info("Deleting es's data, index: {}, id: {}", indexName, id);
        return esTransportService.deleteDataById(indexName, id);
    }
}
