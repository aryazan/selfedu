package com.a1qa.controller;

import com.a1qa.model.Request;
import com.a1qa.rest.RestClient;
import com.a1qa.rest.RestClientResponse;
import com.a1qa.service.RequestService;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class RequestController {
    CloseableHttpClient httpclient = HttpClients.createDefault();

    @Autowired
    private RequestService requestService;

//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public Collection<Request> getAllStudents() {
//        HttpGet httpGet = new HttpGet("http://httpbin.org/status/200");
//        String response = "NULL";
//        try {
//            response = httpclient.execute(httpGet).getEntity().toString();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        requestService.saveRequest(new Request(123, response));
//        return requestService.getAllRequests();
//    }

    @GetMapping(value = "/")
    public String startApp(Model model) throws InterruptedException, IOException {
        RestClient restClient = new RestClient();
        RestClientResponse restClientResponse = restClient.runGetRequest(new HttpGet("http://httpbin.org/status/200"));
        requestService.saveRequest(new Request(1, restClientResponse.getBody()));
//        int i = 1;
//        while (i < 100){
//            Thread.sleep(1000);
//            HttpGet httpGet = new HttpGet("http://httpbin.org/status/200");
//            String response = "NULL";
//            try {
//                response = httpclient.execute(httpGet).getEntity().toString();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            requestService.saveRequest(new Request(i, response));
//            i++;
//        }
        return "index";
    }
}
