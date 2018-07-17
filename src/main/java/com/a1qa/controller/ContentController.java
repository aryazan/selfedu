package com.a1qa.controller;

import com.a1qa.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContentController {

    @Autowired
    private RequestService requestService;

    @GetMapping(path = "/content")
    public String openDbContent(Model model) {
        model.addAttribute("requests", requestService.getAllRequests());
        return "content";
    }
}
