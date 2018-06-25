package com.a1qa.service;

import com.a1qa.dao.IMongoWrapper;
import com.a1qa.model.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class RequestService {

    @Autowired
    private IMongoWrapper iMongoWrapper;

    public Collection<Request> getAllRequests() {
        return iMongoWrapper.findAll();
    }

    public void saveRequest(Request request) {
        iMongoWrapper.save(request);
    }
}
