package com.a1qa.controller;

import com.a1qa.model.Config;
import com.a1qa.service.ConfigService;
import com.a1qa.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ConfigController {

    @Autowired
    private RequestService requestService;
    @Autowired
    private ConfigService configService;

    @PostMapping(path = "/config")
    public String setConfig(@RequestParam(defaultValue = "10", name = "count") int count, Model model) {
        Config config = new Config(count);
        configService.saveConfig(config);
        model.addAttribute("config", config);
        requestService.updateRequestsCount();
        return "config";
    }

    @GetMapping(path = "/config")
    public String showConfig(Model model) {
        model.addAttribute("config", configService.getConfig());
        return "config";
    }
}
