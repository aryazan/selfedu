package com.a1qa.dao;

import com.a1qa.model.Config;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigRepo extends MongoRepository<Config, String> {
    Config findConfigById(String id);
}
