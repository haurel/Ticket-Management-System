package com.common.objects.models.request;

public record RequestModel(String baseUrl,
    String controller,
    String action,
    String requestBody,
    String contentType
) {
    public RequestModel {
        if (contentType == null || contentType.isEmpty()) {
            contentType = "application/json";
        }
    }
}
