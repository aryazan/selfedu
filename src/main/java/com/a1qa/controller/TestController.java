package com.a1qa.controller;

import com.a1qa.model.Request;
import com.a1qa.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//#TODO for testing Chart JS

@RestController
public class TestController {

    @Autowired
    private RequestService requestService;

    @RequestMapping(path = "/test")
    public List<Request> lala(Model model) {
        model.addAttribute("requests", requestService.getAllRequests());
        return (List<Request>) requestService.getAllRequests();
    }
}
