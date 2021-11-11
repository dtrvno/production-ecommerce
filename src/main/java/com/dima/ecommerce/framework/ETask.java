package com.dima.ecommerce.framework;

import com.dima.ecommerce.configuration.ECommerceConfiguration;
import com.dima.ecommerce.utils.ECommandReturn;
import com.dima.ecommerce.utils.ECommerceCommand;
import com.dima.ecommerce.utils.ECommerceException;
import com.dima.ecommerce.utils.EJsonUtils;
import org.json.simple.JSONObject;

public abstract class ETask {
    private final String taskName="";
    private Object jsonConfig=null;
    protected ECommerceCommand command= new ECommerceCommand();
    public abstract void create() throws ECommerceException ;


    public Object getJsonConfig() {
        return jsonConfig;
    }

    public void setJsonConfig(Object jsonConfig) {
        this.jsonConfig = jsonConfig;
    }

    public String getTaskName() {
        return taskName;
    }
    public abstract void updateStatus() ;
    public void saveStatusFile() {
        EJsonUtils.jsonToFile(ECommerceConfiguration.configuration,ECommerceConfiguration.path);
    }
    public JSONObject execute(String s) throws ECommerceException
    {
        ECommandReturn ret= command.execute(s);
        if (ret.status == ECommandReturn.ECommandStatus.OK) {
            if (ret.output.isEmpty())
                return new JSONObject();
            JSONObject obj = EJsonUtils.parse(ret.output);
            return obj;
        }
        else throw new ECommerceException(ret.error_output);
    }
}
