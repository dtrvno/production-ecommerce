package com.dima.ecommerce.flow;

import com.dima.ecommerce.configuration.ECommerceConfiguration;
import com.dima.ecommerce.framework.ETask;
import com.dima.ecommerce.utils.ECommerceException;
import com.dima.ecommerce.utils.ECommerceLogging;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class EFlow {
    public EFlow() {
        EInit.initialize();
        ECommerceFactory.registerInstances();
    }
    public void create() {
        try {
            for (Object object: ECommerceConfiguration.steps) {
                JSONObject obj=(JSONObject)object;
                String taskName=(String) obj.keySet().toArray()[0];
                ETask task=ECommerceFactory.getTaskInstance(taskName);
                if(obj.get(taskName) instanceof JSONObject)
                   task.setJsonConfig((JSONObject) obj.get(taskName));   // set json config for certain task
                else
                    task.setJsonConfig((JSONArray) obj.get(taskName));   // set json array config for certain task
                task.create();
                task.updateStatus();
                task.saveStatusFile();
                ECommerceLogging.info(String.format("Executed task %s",taskName));
            }

        }
        catch(ECommerceException e) {
            ECommerceLogging.error(e.getMessage());
        }

        /*
        * create vpc
        * create subnet(2 public, 2 private)
        * create security group
        * create route tables
        * create NAT gateway
        * create INT gateway
        *
         */
    }
}
