package com.a1qa.utils;

import aqa.properties.PropertiesResourceManager;

public class Constants {
    private final static PropertiesResourceManager REQUESTS_PROPS = new PropertiesResourceManager("requests.properties");
    public final static int DEFAULT_REQUESTS_COUNT = Integer.parseInt(REQUESTS_PROPS.getProperty("default.requests.count"));
}
