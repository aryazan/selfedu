package com.a1qa.controller;

import com.a1qa.service.ConfigService;
import com.a1qa.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class MainController {

    @Autowired
    private ConfigService configService;

    @Autowired
    private RequestService requestService;

    @GetMapping(path = "/")
    public String startApp(Model model) throws IOException {
        configService.setDefaultConfigIfNotExist();
        requestService.updateRequestsCount();
        return "index";
    }


}
