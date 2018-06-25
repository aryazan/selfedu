package com.a1qa.model;

import org.springframework.data.annotation.Id;

public class Request {

    @Id
    private int responseTime;
    private String response;
    private int id;

    public Request(int responseTime, String response) {
        this.responseTime = responseTime;
        this.response = response;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(int responseTime) {
        this.responseTime = responseTime;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

}
