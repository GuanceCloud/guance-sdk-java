package com.guance.openapi;

public class ApiResponseEnvelope {
    public final int httpStatus;
    public final String rawBody;
    public final String contentJson;
    public final boolean success;
    public final String errorCode;
    public final String message;
    public final String traceId;

    public ApiResponseEnvelope(int httpStatus, String rawBody, String contentJson, boolean success, String errorCode, String message, String traceId) {
        this.httpStatus = httpStatus;
        this.rawBody = rawBody;
        this.contentJson = contentJson;
        this.success = success;
        this.errorCode = errorCode;
        this.message = message;
        this.traceId = traceId;
    }
}
