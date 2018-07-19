package com.a1qa.dao;

import com.a1qa.model.Request;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RequestsRepo extends MongoRepository<Request, String> {

    public List<Request> findByRequestUrlLike(String url);

}
