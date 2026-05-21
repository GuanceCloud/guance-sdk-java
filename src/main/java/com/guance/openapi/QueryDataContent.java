package com.guance.openapi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryDataContent {
    public final String rawJson;
    public final List<QueryDataResult> data = new ArrayList<>();
    public final Map<String, Object> declaration = new HashMap<>();

    private QueryDataContent(String rawJson) {
        this.rawJson = rawJson == null ? "" : rawJson;
    }

    public static QueryDataContent fromJson(String rawJson) {
        QueryDataContent content = new QueryDataContent(rawJson);
        Object parsed = JsonValue.parse(rawJson);
        if (!(parsed instanceof Map)) {
            return content;
        }
        Map<?, ?> root = (Map<?, ?>) parsed;
        Object declarationValue = root.get("declaration");
        if (declarationValue instanceof Map) {
            for (Map.Entry<?, ?> entry : ((Map<?, ?>) declarationValue).entrySet()) {
                content.declaration.put(String.valueOf(entry.getKey()), entry.getValue());
            }
        }
        Object dataValue = root.get("data");
        if (dataValue instanceof List) {
            for (Object item : (List<?>) dataValue) {
                if (item instanceof Map) {
                    content.data.add(QueryDataResult.fromMap((Map<?, ?>) item));
                }
            }
        }
        return content;
    }
}
