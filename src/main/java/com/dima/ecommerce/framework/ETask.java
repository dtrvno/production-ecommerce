package com.dima.ecommerce.framework;

import com.dima.ecommerce.configuration.ECommerceConfiguration;
import com.dima.ecommerce.utils.ECommerceException;
import com.dima.ecommerce.utils.EJsonUtils;
import org.json.simple.JSONObject;

public abstract class ETask {
    private final String taskName="";
    private JSONObject jsonConfig=null;
    public void create() throws ECommerceException {
    }

    public JSONObject getJsonConfig() {
        return jsonConfig;
    }

    public void setJsonConfig(JSONObject jsonConfig) {
        this.jsonConfig = jsonConfig;
    }

    public String getTaskName() {
        return taskName;
    }
    public abstract void updateStatus() ;
    public void saveStatusFile() {
        EJsonUtils.jsonToFile(ECommerceConfiguration.configuration,ECommerceConfiguration.path);
    }
}
