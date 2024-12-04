package com.viajaYa.viajaYa.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//[Patron Singleton]
public class ApplicationLogger {
    private static ApplicationLogger instance;
    private final Logger logger;

    private ApplicationLogger() {
        this.logger = LoggerFactory.getLogger(ApplicationLogger.class);
    }

    public static synchronized ApplicationLogger getInstance() {
        if (instance == null) {
            instance = new ApplicationLogger();
        }
        return instance;
    }

    public Logger getLogger() {
        return logger;
    }
}
