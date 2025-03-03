package com.example.test.response;

public  class ResponseData {
    private String message;
    private Object data;

    public ResponseData() {
    }

    public ResponseData(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}