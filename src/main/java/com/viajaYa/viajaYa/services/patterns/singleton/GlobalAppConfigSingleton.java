package com.viajaYa.viajaYa.services.patterns.singleton;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Properties;

@Component
public class GlobalAppConfigSingleton {

    private static GlobalAppConfigSingleton instance;

    private final String appName;
    private final String appVersion;

    private String apiKey;

    private GlobalAppConfigSingleton() {
        this.appName = "ViajaYa";
        this.appVersion = "1.0.0";
        loadApiKey();
    }

    public static synchronized GlobalAppConfigSingleton getInstance() {
        if (instance == null) {
            instance = new GlobalAppConfigSingleton();
        }
        return instance;
    }

    private void loadApiKey() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            this.apiKey = properties.getProperty("api.key");
        } catch (Exception e) {
            throw new RuntimeException("Error loading API Key from application.properties", e);
        }
    }

    public String getAppName() {
        return appName;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public String getApiKey() {
        return apiKey;
    }
}
