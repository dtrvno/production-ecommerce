package com.dima.ecommerce.flow;

import com.dima.ecommerce.configuration.ECommerceConfiguration;
import com.dima.ecommerce.deployment.ECommerceFactory;
import com.dima.ecommerce.deployment.ECommerceSubnet;
import com.dima.ecommerce.deployment.ECommerceVPC;
import com.dima.ecommerce.deployment.EInit;
import com.dima.ecommerce.framework.ETask;
import com.dima.ecommerce.utils.ECommerceException;
import com.dima.ecommerce.utils.ECommerceLogging;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class EFlow {
    public EFlow() {
        ECommerceFactory.registerInstances();
    }
    public void create() {
        try {
            for (Object object: ECommerceConfiguration.steps) {
                JSONObject obj=(JSONObject)object;
                String taskName=(String) obj.keySet().toArray()[0];
                ETask task=ECommerceFactory.getTaskInstance(taskName);
                task.create();
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
