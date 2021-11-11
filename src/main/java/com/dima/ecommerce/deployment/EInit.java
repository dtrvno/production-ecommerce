package com.dima.ecommerce.deployment;

import com.dima.ecommerce.configuration.ECommerceConfiguration;
import com.dima.ecommerce.framework.ETask;
import com.dima.ecommerce.utils.ECommerceException;
import com.dima.ecommerce.utils.ECommerceLogging;

public class EInit extends ETask {
    static  String configurationFile="/Users/dima/angular/ecommerce/ECommerceProduction/ecommerce_status.json";
    public void execute() throws ECommerceException {
        ECommerceConfiguration.loadConfiguration(configurationFile);
        ECommerceLogging.info("Deployment has been initialized");
    }
}
