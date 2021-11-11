package com.dima.ecommerce.deployment;

import com.dima.ecommerce.configuration.ECommerceConfiguration;
import com.dima.ecommerce.framework.ETask;
import com.dima.ecommerce.utils.ECommerceException;
import com.dima.ecommerce.utils.ECommerceLogging;

public class EInit extends ETask {
    static  String configurationFile="/Users/dima/angular/ecommerce/ECommerceProduction/ecommerce_status.json";
    private final String taskName="init";
    private boolean isLoaded=false;
    public void create() throws ECommerceException {
        if(isLoaded) {
            ECommerceLogging.info("Configuration already loaded");
        }
        ECommerceConfiguration.loadConfiguration(configurationFile);
        ECommerceLogging.info("Deployment has been initialized");
    }

    public EInit() {
       if(!isLoaded) {
           try {
               create();
               isLoaded=true;
           }
           catch (ECommerceException e ) {
               ECommerceLogging.error(e.getMessage());
               System.exit(1);
           }
       }
    }

    public String getTaskName() {
        return taskName;
    }
}
