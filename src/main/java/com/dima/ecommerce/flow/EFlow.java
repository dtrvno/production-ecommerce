package com.dima.ecommerce.flow;

import com.dima.ecommerce.deployment.ECommerceFactory;
import com.dima.ecommerce.deployment.ECommerceSubnet;
import com.dima.ecommerce.deployment.ECommerceVPC;
import com.dima.ecommerce.deployment.EInit;
import com.dima.ecommerce.framework.ETask;
import com.dima.ecommerce.utils.ECommerceException;
import com.dima.ecommerce.utils.ECommerceLogging;

public class EFlow {
    public EFlow() {
        ECommerceFactory.registerInstances();
    }
    public void create() {
        try {
            EInit eInit = new EInit();
            eInit.create();
            ETask vpcTask = new ECommerceVPC();
            vpcTask.create();
            ETask subnetTask = new ECommerceSubnet();
            subnetTask.create();
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
