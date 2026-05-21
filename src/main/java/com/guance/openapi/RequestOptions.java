package com.guance.openapi;

import java.util.HashMap;
import java.util.Map;

public class RequestOptions {
    public Map<String, Object> path = new HashMap<>();
    public Map<String, Object> query = new HashMap<>();
    public Map<String, String> headers = new HashMap<>();
    public String bodyJson;

    public static RequestOptions create() {
        return new RequestOptions();
    }

    public static RequestOptions withQuery(Map<String, Object> query) {
        RequestOptions options = new RequestOptions();
        options.query.putAll(query);
        return options;
    }
}
