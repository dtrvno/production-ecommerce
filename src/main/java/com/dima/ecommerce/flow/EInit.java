package com.dima.ecommerce.flow;

import com.dima.ecommerce.configuration.ECommerceConfiguration;
import com.dima.ecommerce.framework.ETask;
import com.dima.ecommerce.utils.ECommerceException;
import com.dima.ecommerce.utils.ECommerceLogging;
import org.json.simple.JSONObject;

public class EInit  {

    public static void initialize() {
        try {
            ECommerceConfiguration.loadConfiguration();
            ECommerceLogging.info("Deployment has been initialized");
        }
        catch (ECommerceException e) {
            ECommerceLogging.error(e.getMessage());
            System.exit(1);
        }
    }

}
