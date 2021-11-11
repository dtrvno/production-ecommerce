package com.dima.ecommerce.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class ECommerceLogging {
    private static Logger logger=null;
    static {
        InputStream stream = ECommerceLogging.class.getClassLoader().
                getResourceAsStream("logging.properties");
        logger=Logger.getLogger(ECommerceLogging.class.getName());
        try {
            LogManager.getLogManager().readConfiguration(stream);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void info(String message) {
        logger.info(message);
    }
    public static void error(String message) {
        logger.severe(message);
    }
    public static void warning(String message) {
        logger.warning(message);
    }

}