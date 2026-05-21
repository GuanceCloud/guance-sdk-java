package com.guance.openapi;

public class ApiException extends RuntimeException {
    public final int httpStatus;
    public final String errorCode;
    public final String traceId;
    public final ApiResponseEnvelope envelope;

    public ApiException(ApiResponseEnvelope envelope) {
        super(envelope.message == null || envelope.message.isEmpty() ? "OpenAPI request failed" : envelope.message);
        this.httpStatus = envelope.httpStatus;
        this.errorCode = envelope.errorCode;
        this.traceId = envelope.traceId;
        this.envelope = envelope;
    }
}
