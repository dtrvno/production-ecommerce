package com.dima.ecommerce.deployment;

import com.dima.ecommerce.framework.ETask;
import com.dima.ecommerce.utils.ECommerceException;

import java.util.Hashtable;

public class ECommerceFactory {
    private static Hashtable<String, ETask>  map = new Hashtable<>();
// we need to register this automatically based on package content...
    public static void registerInstances() {
        ETask  task= new EInit();
        map.put(task.getTaskName(),task);
        task = new ECommerceSubnet();
        map.put(task.getTaskName(),task);
        task=new ECommerceVPC();
        map.put(task.getTaskName(),task);
    }
    public static ETask getTaskInstance(String name)  throws ECommerceException {
        ETask task = map.get(name);
        if (task==null) throw new ECommerceException(String.format("Task %s does not exist in configuration",name));
        return task;
    }
}
