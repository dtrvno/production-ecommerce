package com.dima.ecommerce.deployment;

import com.dima.ecommerce.configuration.ECommerceConfiguration;
import com.dima.ecommerce.framework.ETask;
import com.dima.ecommerce.utils.ECommerceException;
import com.dima.ecommerce.utils.ECommerceLogging;
import org.json.simple.JSONObject;

public class EInit extends ETask {
    private final String taskName="init";
    private boolean isLoaded=false;

    public void create() throws ECommerceException {
        if(isLoaded) {
            ECommerceLogging.info("Configuration has been loaded");
            return;
        }
        ECommerceConfiguration.loadConfiguration();
        ECommerceLogging.info("Deployment has been initialized");
    }
    public void updateStatus() {
        JSONObject conf=getJsonConfig();
        ((JSONObject)conf.get(taskName)).put("status",true);
        saveStatusFile();
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
