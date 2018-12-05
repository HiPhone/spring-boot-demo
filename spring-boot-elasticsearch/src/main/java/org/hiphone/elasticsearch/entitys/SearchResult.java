package org.hiphone.elasticsearch.entitys;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author HiPhone
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResult {

    public static final SearchResult EMPTY_RESULT = new SearchResult(0);

    @Getter
    @JSONField(name = "total_size")
    private final Integer totalSize;

    private final List<Item> items0;

    @Getter
    private final List<Item> items;

    public SearchResult(Integer totalSize) {
        this.totalSize = totalSize;
        this.items0 = new ArrayList<>(totalSize);
        this.items = Collections.unmodifiableList(items0);
    }

    public static boolean isEmpty(SearchResult result) {
        return result == null || result.getTotalSize() == 0;
    }

    public static boolean isNotEmpty(SearchResult result) {
        return !isEmpty(result);
    }

    public void add(Map<String, Object> source, Map<String, String> hightlights) {
        items0.add(new Item(source, hightlights));
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Item {

        @Getter
        private final Map<String, Object> source;

        @Getter
        private final Map<String, String> highlights;

        Item( Map<String, Object> source, Map<String, String> highlights) {
            this.source = source;
            this.highlights = highlights;
        }

    }
}
