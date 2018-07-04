package com.a1qa.service;

import com.a1qa.dao.IMongoWrapper;
import com.a1qa.model.Request;
import com.a1qa.rest.RestClient;
import com.a1qa.rest.RestClientResponse;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.http.client.methods.HttpGet;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class RequestService {

    @Autowired
    private IMongoWrapper iMongoWrapper;

    private final static int REQUESTS_COUNT = 50;

    AsyncHttpClient asyncHttpClient = new DefaultAsyncHttpClient();

    public Collection<Request> getAllRequests() {
        return iMongoWrapper.findAll();
    }

    public List<Request> getRequestsByUrl(String url) {
        return iMongoWrapper.findByRequestUrl(url);
    }

    public void saveRequest(Request request) {
        iMongoWrapper.save(request);
    }

    public void sendRequestAsync(String url){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            try {
                sendRequest(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void sendRequest(String requestUrl) throws IOException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        RestClient restClient = new RestClient();
        RestClientResponse restClientResponse = restClient.runGetRequest(new HttpGet(requestUrl));
        stopWatch.stop();
        System.out.println(String.format("Request status  [ %s ], time - [ %s ]", restClientResponse.getRequestUri() , stopWatch.getTime()));
        saveRequest(new Request(stopWatch.getTime(), restClientResponse.getRequestUri()));
    }

    public void sendRequests() throws IOException, ExecutionException, InterruptedException {
//        RestClient restClient = new RestClient();

        while (getAllRequests().size() < 1000) {
            sendRequestAsync("http://172.20.69.45/status/200");
            sendRequestAsync("http://172.20.69.45/delay/1");
//            StopWatch stopWatch = new StopWatch();
//            stopWatch.start();
//            System.out.println(getAllRequests().size());

//            CompletableFuture<Response> whenResponse = asyncHttpClient.prepareGet("http://httpbin.org/status/200")
//                    .execute()
//                    .toCompletableFuture()
//                    .thenApply(response -> {
//                        System.out.println(String.format("Request status  [ %s ], time - [ %s ]", "(first request)" , stopWatch.getTime()));
//                        System.out.println(response.getUri().toUrl());
//                        saveRequest(new Request(stopWatch.getTime(), response.getUri().toUrl()));
//                        System.out.println("FIRST COMPLETED");
//                        return response;});

//            whenResponse.join();
//            stopWatch.stop();
//            System.out.println(String.format("Request status 200 [ %s ], time - [ %s ]", i + 1 , stopWatch.getTime()));
//            saveRequest(new Request(stopWatch.getTime(), whenResponse.get().getUri().toUrl()));
//            stopWatch.reset();
//            stopWatch.start();

//            CompletableFuture<Response> secondResponse = asyncHttpClient.prepareGet("http://httpbin.org/delay/1")
//                    .execute()
//                    .toCompletableFuture()
//                    .thenApply(response -> {
//                        System.out.println(String.format("Request delay  [ %s ], time - [ %s ]", "(second request)" , stopWatch.getTime()));
//                        System.out.println(response.getUri().toUrl());
//                        saveRequest(new Request(stopWatch.getTime(), response.getUri().toUrl()));
//                        System.out.println("SECOND COMPLETED");
//                        return response;
//                    });

//            secondResponse.join();
//            stopWatch.stop();
//            System.out.println(String.format("Request delay  [ %s ], time - [ %s ]", i + 1 , stopWatch.getTime()));
//            saveRequest(new Request(stopWatch.getTime(), secondResponse.get().getUri().toUrl()));
//            stopWatch.reset();
//            stopWatch.start();
  //          i++;

//        }
        }
    }
}
