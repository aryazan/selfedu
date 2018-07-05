package com.a1qa.service;

import aqa.logger.Logger;
import com.a1qa.dao.RequestsRepo;
import com.a1qa.model.Request;
import com.a1qa.rest.RestClient;
import com.a1qa.rest.RestClientResponse;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.http.client.methods.HttpGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

@Service
public class RequestService {

    private final Logger logger = Logger.getInstance();

    @Autowired
    private RequestsRepo requestsRepo;
    private final static int REQUESTS_COUNT = 1000;
    private final static int ONE_MINUTE_IN_MS = 60000;

    public Collection<Request> getAllRequests() {
        return requestsRepo.findAll();
    }

    public List<Request> getRequestsByUrl(String url) {
        return requestsRepo.findByRequestUrl(url);
    }

    public void saveRequest(Request request) {
        requestsRepo.save(request);
    }

    public void sendRequestInNewThread(String url) {
        new Thread(() -> {
            try {
                StopWatch stopWatch = new StopWatch();
                stopWatch.start();
                RestClientResponse restClientResponse = sendGetRequest(url);
                stopWatch.stop();
                saveRequestToDb(new Request(stopWatch.getTime(), restClientResponse.getRequestUri()));
            } catch (IOException e) {
                logger.warn("Error while sending request request");
                e.printStackTrace();
            }
        }).start();
    }

    public RestClientResponse sendGetRequest(String url) throws IOException {
        return new RestClient().runGetRequest(new HttpGet(url));
    }

    public void saveRequestToDb(Request request) {
        System.out.println(String.format("Request status  [%s], time : [ %s ms]", request.getRequestUrl(), request.getResponseTime()));
        saveRequest(request);
    }

    public void sendRequests() {
        //#TODO переместить очистку отсюда
        requestsRepo.deleteAll();
        while (getAllRequests().size() < REQUESTS_COUNT) {
            sendRequestInNewThread("http://172.20.69.45/status/200");
            sendRequestInNewThread(String.format("http://172.20.69.45/delay/%s", RandomUtils.nextInt(1, 6)));
            try {
                Thread.sleep(ONE_MINUTE_IN_MS / REQUESTS_COUNT);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
