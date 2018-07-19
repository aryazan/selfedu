package com.a1qa.service;

import aqa.properties.PropertiesResourceManager;
import com.a1qa.dao.ConfigRepo;
import com.a1qa.model.Config;
import com.a1qa.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigService {
    @Autowired
    private ConfigRepo configRepo;

    private final static PropertiesResourceManager REQUESTS_PROPS = new PropertiesResourceManager("requests.properties");
    private final static String CONFIG_ID = Constants.CONFIG_ID;
    private Config defaultConfig = new Config(Integer.parseInt(REQUESTS_PROPS.getProperty("default.requests.count")));

    public void saveConfig(Config config) {
        configRepo.save(config);
    }

    public Config getConfig() {
        return configRepo.findConfigById(CONFIG_ID);
    }

    public void setDefaultConfigIfNotExist() {
        if (configRepo.count() == 0)
            saveConfig(defaultConfig);
    }


}
