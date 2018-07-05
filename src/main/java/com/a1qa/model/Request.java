package com.a1qa.model;

import org.springframework.data.annotation.Id;

public class Request {

    @Id
    private String id;
    private long responseTime;
    private String requestUrl;


    public Request(long responseTime, String requestUrl) {
        this.responseTime = responseTime;
        this.requestUrl = requestUrl;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public long getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(int responseTime) {
        this.responseTime = responseTime;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setResponseTime(long responseTime) {
        this.responseTime = responseTime;
    }
}
