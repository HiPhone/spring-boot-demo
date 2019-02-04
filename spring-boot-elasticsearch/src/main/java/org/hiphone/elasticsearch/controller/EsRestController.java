package org.hiphone.elasticsearch.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.hiphone.elasticsearch.entitys.ResultMessage;
import org.hiphone.elasticsearch.service.EsRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author HiPhone
 */
@Slf4j
@RestController
@RequestMapping(value = "/es")
@Api(value = "EsRestController", description = "使用RESTful操作Es的Controller")
public class EsRestController {

    @Autowired
    private EsRestService esRestService;

    @PostMapping("/index/generate")
    @ApiOperation(value = "添加es的索引和类型的接口", notes = "发起PUT请求增加索引和类型")
    public ResultMessage generateIndex(@RequestParam(name = "indexName") @ApiParam(name = "indexName", value = "索引名称") String indexName,
                                       @RequestBody @ApiParam(name = "mappings", value = "ES索引结构的demo") JSONObject mappings) {
        log.info("Received a request for creating a new index, index name is: {}, mappings is {}", indexName, mappings);
        return esRestService.createEsMapping(indexName, mappings);
    }

    @DeleteMapping("/index/delete/{indexName}")
    @ApiOperation(value = "删除es的索引", notes = "根据索引名发起请求删除ES中的索引")
    public ResultMessage deleteIndex(@PathVariable(value = "indexName") @ApiParam(name = "indexName", value = "索引名称") String indexName) {
        log.info("Received a request for deleting index, index name is: {}", indexName);
        return esRestService.deleteEsMapping(indexName);
    }

    @PostMapping("/data/add/{indexName}/{type}")
    @ApiOperation(value = "向ES中添加单条数据的接口", notes = "根据索引名和类型添加传入的数据")
    public ResultMessage addDataToIndex(@PathVariable(value = "indexName") @ApiParam(name = "indexName", value = "索引名称") String indexName,
                                        @PathVariable(value = "type") @ApiParam(name = "type", value = "文档存放的类型") String type,
                                        @RequestBody @ApiParam(name = "data", value = "添加的数据") Map<String, String> data) {
        log.info("Received a request for adding a new data to index: {}, type is {}, data is {}", indexName, type, data);
        return esRestService.addDataToIndex(indexName, type, data);
    }

    @DeleteMapping("/data/delete/{indexName}/{type}/{id}")
    @ApiOperation(value = "删除es中对应索引和类型的单条数据", notes = "根据id、索引名和类型删除es中的数据")
    public ResultMessage deleteDataFromIndex(@PathVariable(value = "indexName") @ApiParam(name = "indexName", value = "索引名称") String indexName,
                                             @PathVariable(value = "type") @ApiParam(name = "type", value = "文档存放的类型") String type,
                                             @PathVariable(value = "id") @ApiParam(name = "id", value = "需要删除的文档的id") String id) {
        log.info("Received a request for deleting data from index: {}, type: {}, data's id is {}", indexName, type, id);
        return esRestService.deleteDataFromIndex(indexName, type, id);
    }

    @PutMapping("/data/update/{indexName}/{type}/{id}")
    @ApiOperation(value = "更新es中对应索引中的单条数据", notes = "根据id、索引名和类型更新es中的数据")
    public ResultMessage updateDataFromIndex(@PathVariable(value = "indexName") @ApiParam(name = "indexName", value = "索引名称") String indexName,
                                             @PathVariable(value = "type") @ApiParam(name = "type", value = "文档存放的类型") String type,
                                             @PathVariable(value = "id") @ApiParam(name = "id", value = "文档的id") String id,
                                             @RequestBody @ApiParam(name = "data", value = "新的数据") Map<String, String> data) {
        log.info("Received a request for updating data from index: {}, type: {}, data's id is {}, new data is {}", indexName, type, id, data);
        return esRestService.updateDataFromIndex(indexName, type, id, data);
    }
}
