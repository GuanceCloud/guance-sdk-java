package com.guance.openapi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QueryDataSeries {
    public List<String> columns = new ArrayList<>();
    public String name;
    public List<Object> units = new ArrayList<>();
    public List<List<Object>> values = new ArrayList<>();
    public Map<String, Object> raw;

    static QueryDataSeries fromMap(Map<?, ?> source) {
        QueryDataSeries series = new QueryDataSeries();
        series.raw = JsonValue.toStringObjectMap(source);
        series.columns = JsonValue.toStringList(source.get("columns"));
        series.name = JsonValue.asString(source.get("name"));
        series.units = JsonValue.toObjectList(source.get("units"));
        Object valuesObj = source.get("values");
        if (valuesObj instanceof List) {
            for (Object row : (List<?>) valuesObj) {
                series.values.add(JsonValue.toObjectList(row));
            }
        }
        return series;
    }
}
