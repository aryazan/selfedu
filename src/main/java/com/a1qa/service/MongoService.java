package com.a1qa.service;

import com.mongodb.Mongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;

public class MongoService {
    private @Autowired
    Mongo mongo;
    private @Autowired
    MongoDbFactory mongoDbFactory;

    public void clearDb(){
        mongo.dropDatabase(mongoDbFactory.getDb().getName());
    }
}
