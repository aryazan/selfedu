package com.a1qa.service;

import aqa.properties.PropertiesResourceManager;
import com.a1qa.dao.ConfigRepo;
import com.a1qa.dao.RequestsRepo;
import com.a1qa.model.Config;
import com.a1qa.model.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class DatabaseService {

    @Autowired
    private RequestsRepo requestsRepo;

    @Autowired
    private ConfigRepo configRepo;

    private final static PropertiesResourceManager REQUESTS_PROPS = new PropertiesResourceManager("requests.properties");
    private Config defaultConfig = new Config(Integer.parseInt(REQUESTS_PROPS.getProperty("default.requests.count")));

    public void saveConfig(Config config) {
        configRepo.save(config);
    }

    public void saveDefaultConfig() {
        saveConfig(defaultConfig);
    }

    public void clearDb() {
        requestsRepo.deleteAll();
    }

    public Collection<Request> getAllRequests() {
        return requestsRepo.findAll();
    }

    public Config getActualConfig() {
        return configRepo.findAll().get(0);
    }

    public Optional<Config> getConfigById(String id){
        return configRepo.findById(id);
    }


}
