package com.a1qa.dao;

import com.a1qa.model.Request;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IMongoWrapper extends MongoRepository<Request, String> {
}
