package com.a1qa.controller;

import com.a1qa.model.Config;
import com.a1qa.service.DatabaseService;
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
    @Autowired
    private DatabaseService databaseService;

    @GetMapping(path = "/")
    public String startApp(Model model) throws IOException {
        //#TODO: удалить очистку после выполнения задания
        databaseService.clearDb();
        databaseService.saveConfig(new Config());
        return "index";
    }

    @GetMapping(path = "/start")
    public String sendRequests() throws IOException, ExecutionException, InterruptedException {
        requestService.startSendRequests();
        requestService.sendRequests();
        return "index";
    }

    @GetMapping(path = "/stop")
    public String stopSendingRequests() {
        requestService.stopSendRequests();
        return "index";
    }

}
