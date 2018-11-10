package org.hiphone.elasticsearch.controller;

import com.alibaba.fastjson.JSONObject;
import org.hiphone.elasticsearch.entitys.ResultMessage;
import org.hiphone.elasticsearch.service.EsRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author HiPhone
 */
@RestController
@RequestMapping(value = "/es")
public class EsRestController {

    @Autowired
    private EsRestService esRestService;

    @ResponseBody
    @PutMapping("/index/generate")
    public ResultMessage generateIndex(@RequestParam String indexName, @RequestBody JSONObject mappings) {
        return esRestService.createEsMapping(indexName, mappings);
    }

    @ResponseBody
    @DeleteMapping("/index/delete/{indexName}")
    public ResultMessage deleteIndex(@PathVariable(value = "indexName") String indexName) {
        return esRestService.deleteEsMapping(indexName);
    }

    @ResponseBody
    @PostMapping("/data/add/{indexName}/{type}")
    public ResultMessage addDataToIndex(@PathVariable(value = "indexName") String indexName, @PathVariable(value = "type") String type,  @RequestBody JSONObject data) {
        return esRestService.addDataToIndex(indexName, type, data);
    }

    @ResponseBody
    @DeleteMapping("/data/delete/{indexName}/{type}/{id}")
    public ResultMessage deleteDataFromIndex(@PathVariable(value = "indexName") String indexName, @PathVariable(value = "type") String type, @PathVariable(value = "id") String id) {
        return esRestService.deleteDataFromIndex(indexName, type, id);
    }
}
