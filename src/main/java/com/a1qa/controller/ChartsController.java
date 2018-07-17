package com.a1qa.controller;

import com.a1qa.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChartsController {

    @Autowired
    private RequestService requestService;

    @GetMapping(path = "/charts")
    public String charts(Model model) {
        model.addAttribute("dataArray", requestService.getAllRequests());
        return "charts";
    }
}
