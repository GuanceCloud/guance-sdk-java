package com.guance.openapi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QueryDataResult {
    public String asyncId;
    public List<String> columnNames = new ArrayList<>();
    public Boolean complete;
    public String cost;
    public String indexName;
    public String indexNames;
    public String indexStoreType;
    public Long interval;
    public Boolean isRunning;
    public Long maxPoint;
    public Double nextCursorTime;
    public Object points;
    public String queryType;
    public String rawQuery;
    public Double sample;
    public Boolean scanCompleted;
    public String scanIndex;
    public List<Object> searchAfter = new ArrayList<>();
    public List<QueryDataSeries> series = new ArrayList<>();
    public Long totalHits;
    public Long window;
    public Map<String, Object> raw;

    static QueryDataResult fromMap(Map<?, ?> source) {
        QueryDataResult result = new QueryDataResult();
        result.raw = JsonValue.toStringObjectMap(source);
        result.asyncId = JsonValue.asString(source.get("async_id"));
        result.columnNames = JsonValue.toStringList(source.get("column_names"));
        result.complete = JsonValue.asBoolean(source.get("complete"));
        result.cost = JsonValue.asString(source.get("cost"));
        result.indexName = JsonValue.asString(source.get("index_name"));
        result.indexNames = JsonValue.asString(source.get("index_names"));
        result.indexStoreType = JsonValue.asString(source.get("index_store_type"));
        result.interval = JsonValue.asLong(source.get("interval"));
        result.isRunning = JsonValue.asBoolean(source.get("is_running"));
        result.maxPoint = JsonValue.asLong(source.get("max_point"));
        result.nextCursorTime = JsonValue.asDouble(source.get("next_cursor_time"));
        result.points = source.get("points");
        result.queryType = JsonValue.asString(source.get("query_type"));
        result.rawQuery = JsonValue.asString(source.get("raw_query"));
        result.sample = JsonValue.asDouble(source.get("sample"));
        result.scanCompleted = JsonValue.asBoolean(source.get("scan_completed"));
        result.scanIndex = JsonValue.asString(source.get("scan_index"));
        result.searchAfter = JsonValue.toObjectList(source.get("search_after"));
        result.totalHits = JsonValue.asLong(source.get("total_hits"));
        result.window = JsonValue.asLong(source.get("window"));
        Object seriesValue = source.get("series");
        if (seriesValue instanceof List) {
            for (Object item : (List<?>) seriesValue) {
                if (item instanceof Map) {
                    result.series.add(QueryDataSeries.fromMap((Map<?, ?>) item));
                }
            }
        }
        return result;
    }
}
