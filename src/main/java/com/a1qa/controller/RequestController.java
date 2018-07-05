package com.a1qa.controller;

import com.a1qa.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Controller
public class RequestController {

    @Autowired
    private RequestService requestService;

    @GetMapping(path = "/")
    public String startApp(Model model) throws IOException {
        return "index";
    }

    @GetMapping(path = "/start")
    public String sendRequests() throws IOException, ExecutionException, InterruptedException {
        requestService.sendRequests();
        return "start";
    }

    @GetMapping(path = "/stop")
    public String stopSendingRequests(){
        System.out.println("LOLOMG");
        System.out.println("LOLOMG");
        System.out.println("LOLOMG");
        System.out.println("LOLOMG");
        System.out.println("LOLOMG");
        System.out.println("LOLOMG");
        return "stop";
    }

    @GetMapping(path = "/content")
    public String openDbContent(Model model) {
        model.addAttribute("requests", requestService.getAllRequests());
        return "content";
    }

}
