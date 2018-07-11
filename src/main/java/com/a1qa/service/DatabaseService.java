package com.a1qa.service;

import com.a1qa.dao.RequestsRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class DatabaseService {

    @Autowired
    private RequestsRepo requestsRepo;

    public void clearDb() {
        requestsRepo.deleteAll();
    }
}
