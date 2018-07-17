package com.a1qa.controller;

import com.a1qa.service.DatabaseService;
import com.a1qa.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

public class MainController {

    @Autowired
    private DatabaseService databaseService;

    @Autowired
    private RequestService requestService;

    @GetMapping(path = "/")
    public String startApp(Model model) throws IOException {
        //#TODO: удалить очистку после выполнения задания
        databaseService.clearDb();
        databaseService.setDefaultConfig();
        requestService.updateRequestsCount();
        return "index";
    }
}
