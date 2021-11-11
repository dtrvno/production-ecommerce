package com.dima.ecommerce.deployment;

import com.dima.ecommerce.framework.ETask;
import com.dima.ecommerce.utils.ECommerceException;
import com.dima.ecommerce.utils.ECommerceLogging;
import org.json.simple.JSONObject;

//parameters name and cidr block
public class ECommerceVPC extends ETask {
    private final String taskName="vpc";

    public String getTaskName() {
        return taskName;
    }
//   aws ec2 create-vpc --cidr-block 10.0.0.0/16
 //aws ec2 create-tags --resources vpc-016f7f2119b9e2764 --tags Key=Name,Value=Production
    /*
    "name" : "ApplicationVPC",
        "taskName" : "vpc",
        "cidr" : "10.0.0.0/16",
        "id" : "",
        "status" : false
    */
   public void create() throws ECommerceException {
       String vpcId=createVPC();
       if(vpcId!=null)
          createTag(vpcId);
   }
   public void createTag(String vpcId) throws  ECommerceException {
       JSONObject jsonObject=(JSONObject) getJsonConfig();
       String vpcName= (String) jsonObject.get("name");
       String commandLine=String.format("aws ec2 create-tags --resources %s --tags Key=Name,Value=%s",
               vpcId,vpcName);
       execute(commandLine);    // tag assignment does not return anything
   }
   public String createVPC() throws ECommerceException {
        JSONObject jsonObject=(JSONObject) getJsonConfig();
        String status =(String) jsonObject.get("status");
        String vpcName= (String) jsonObject.get("name");
        if(status.equals("true")) {
            ECommerceLogging.info(String.format("VPC %s already created",vpcName));
            return null;
        }
        String vpcCIDR= (String)jsonObject.get("cidr") ;
        String commandLine=String.format("aws ec2 create-vpc --cidr-block %s",vpcCIDR);
        JSONObject jsonVpc= execute(commandLine);
// vpc has status pending but created quickly, so we do not need to check.
       JSONObject jsonVpcObject=(JSONObject) jsonVpc.get("Vpc");
       String vpcId=(String) jsonVpcObject.get("VpcId");
       jsonObject.put("id",vpcId);
       return vpcId;
   }

   public void updateStatus()  {
       JSONObject jsonObject=(JSONObject) getJsonConfig();
       jsonObject.put("status",true);
  }
}
