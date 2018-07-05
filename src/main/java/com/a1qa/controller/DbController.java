package com.a1qa.controller;

import com.a1qa.dao.RequestsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class DbController {

    @Autowired
    private RequestsRepo requestsRepo;
}
