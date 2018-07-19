package com.a1qa.controller;

import com.a1qa.model.Request;
import com.a1qa.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class RequestController {

    @Autowired
    private RequestService requestService;

    @GetMapping(path = "/start")
    public String sendRequests() {
        requestService.startSendRequests();
        requestService.sendRequests();
        return "index";
    }

    @GetMapping(path = "/stop")
    public String stopSendingRequests() {
        requestService.stopSendRequests();
        return "index";
    }

    @GetMapping(path = "/getData/{urlPart}")
    @ResponseBody
    public List<Request> getRequestsByUrl(@PathVariable("urlPart") String urlPart) {
        return requestService.getRequestByUrlLike(urlPart);
    }
}
