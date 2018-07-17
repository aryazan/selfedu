package com.a1qa.controller;

import com.a1qa.model.Config;
import com.a1qa.service.DatabaseService;
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
    private DatabaseService databaseService;

    //#TODO: все исправить

    @PostMapping(path = "/config")
    public String setConfig(@RequestParam(defaultValue = "10", name = "count") int count, Model model) {
        Config config = new Config(count);
        databaseService.saveConfig(config);
        model.addAttribute("config", config);
        requestService.updateRequestsCount();
        return "config";
    }

    @GetMapping(path = "/config")
    public String showConfig(Model model) {
        model.addAttribute("config", databaseService.getConfig());
        return "config";
    }
}
