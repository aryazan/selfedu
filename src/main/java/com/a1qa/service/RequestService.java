package com.a1qa.service;

import aqa.logger.Logger;
import aqa.properties.PropertiesResourceManager;
import com.a1qa.dao.ConfigRepo;
import com.a1qa.dao.RequestsRepo;
import com.a1qa.model.Config;
import com.a1qa.model.Request;
import com.a1qa.rest.RestClient;
import com.a1qa.rest.RestClientResponse;
import com.a1qa.utils.Constants;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.http.client.methods.HttpGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class RequestService {

    @Autowired
    private RequestsRepo requestsRepo;

    @Autowired
    private ConfigRepo configRepo;

    private final static PropertiesResourceManager REQUESTS_PROPS = new PropertiesResourceManager("requests.properties");
    private final static String APPLICATION_URL = REQUESTS_PROPS.getProperty("application.url");
    private final static String DELAY_URL = String.format(APPLICATION_URL, REQUESTS_PROPS.getProperty("delay.url"));
    private final static String STATUS_200_URL = String.format(APPLICATION_URL, REQUESTS_PROPS.getProperty("status.200.url"));
    private final static String CONFIG_ID = Constants.CONFIG_ID;
    private final static int MIN_DELAY = Integer.parseInt(REQUESTS_PROPS.getProperty("min.delay"));
    private final static int MAX_DELAY = Integer.parseInt(REQUESTS_PROPS.getProperty("max.delay"));
    private final static int ONE_MINUTE_IN_MS = 60000;
    private Config config = null;
    private final Logger logger = Logger.getInstance();
    private boolean sendRequests = true;

    public void updateRequestsCount() {
        this.config = configRepo.findConfigById(CONFIG_ID);
    }

    public List<Request> getRequestsByUrl(String url) {
        return requestsRepo.findByRequestUrl(url);
    }

    public void saveRequest(Request request) {
        requestsRepo.save(request);
    }

    public void saveSendedRequestInDb(String url) {
        new Thread(() -> {
            try {
                StopWatch stopWatch = new StopWatch();
                stopWatch.start();
                RestClientResponse restClientResponse = sendGetRequest(url);
                stopWatch.stop();
                saveRequestToDb(new Request(stopWatch.getTime(), restClientResponse.getRequestUri()));
            } catch (IOException e) {
                logger.warn("Error while sending request");
                e.printStackTrace();
            }
        }).start();
    }

    public RestClientResponse sendGetRequest(String url) throws IOException {
        return new RestClient().runGetRequest(new HttpGet(url));
    }

    public void saveRequestToDb(Request request) {
        logger.info(String.format("Request status  [%s], time : [ %s ms]", request.getRequestUrl(), request.getResponseTime()));
        saveRequest(request);
    }

    public void sendRequests() {
        if (config == null)
            updateRequestsCount();
        while (sendRequests) {
            saveSendedRequestInDb(STATUS_200_URL);
            saveSendedRequestInDb(String.format(DELAY_URL, RandomUtils.nextInt(MIN_DELAY, MAX_DELAY)));
            logger.info("=============");
            logger.info(("Current requests count is: " + config.getCount()).toUpperCase());
            logger.info("=============");
            try {
                Thread.sleep(ONE_MINUTE_IN_MS / config.getCount());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopSendRequests() {
        this.sendRequests = false;
    }

    public void startSendRequests() {
        if (!sendRequests)
            this.sendRequests = true;
    }


}
