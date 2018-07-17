package com.a1qa.controller;

import com.a1qa.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Controller
public class RequestController {

    @Autowired
    private RequestService requestService;

    @GetMapping(path = "/start")
    public String sendRequests() throws IOException, ExecutionException, InterruptedException {
        requestService.startSendRequests();
        requestService.sendRequests();
        return "index";
    }

    @GetMapping(path = "/stop")
    public String stopSendingRequests() {
        requestService.stopSendRequests();
        return "index";
    }

}
