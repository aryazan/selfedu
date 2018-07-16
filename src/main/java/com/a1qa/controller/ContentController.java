package com.a1qa.controller;

import com.a1qa.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContentController {

    @Autowired
    private DatabaseService databaseService;

    @GetMapping(path = "/content")
    public String openDbContent(Model model) {
        model.addAttribute("requests", databaseService.getAllRequests());
        return "content";
    }
}
