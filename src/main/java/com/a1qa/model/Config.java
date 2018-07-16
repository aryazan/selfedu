package com.a1qa.model;

import org.springframework.data.annotation.Id;

public class Config {

    @Id
    private String id;
    private int count;

    public Config(int count) {
        this.count = count;
    }

    public Config() {
    }

    public Config(String id, int count) {
        this.id = id;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Config setDefaultParams(){
        this.count = 25;
        return this;
    }
}
