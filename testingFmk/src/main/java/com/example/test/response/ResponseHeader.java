package com.example.test.response;

public class ResponseHeader {
    private String requestId;
    private String status;
    private String responseCode;
    private String timestamp;
    private String path;
    private String environment;

    public ResponseHeader() {
    }

    public ResponseHeader(String requestId, String status, String responseCode, String timestamp, String path, String environment) {
        this.requestId = requestId;
        this.status = status;
        this.responseCode = responseCode;
        this.timestamp = timestamp;
        this.path = path;
        this.environment = environment;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    @Override
    public String toString() {
        return "ResponseHeader{" +
                "requestId='" + requestId + '\'' +
                ", status='" + status + '\'' +
                ", responseCode='" + responseCode + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", path='" + path + '\'' +
                ", environment='" + environment + '\'' +
                '}';
    }
}