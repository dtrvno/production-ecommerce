package com.dima.ecommerce.configuration;
import com.dima.ecommerce.utils.ECommerceException;
import com.dima.ecommerce.utils.EJsonUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.Ec2Client;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ECommerceConfiguration {

// static parameters
    public static Region region = Region.US_EAST_2;

// generated parameters
    public static Ec2Client ec2 = Ec2Client.builder().region(region).build();
    public static JSONObject configuration=null;
    public static JSONArray steps=null;
    public static  String path="/Users/dima/angular/ecommerce/ECommerceProduction/ecommerce_status.json";
    public static void loadConfiguration() throws  ECommerceException {
        try {
           String content = Files.readString(Paths.get(path), StandardCharsets.US_ASCII);
           JSONObject obj = EJsonUtils.parse(content);
           JSONObject jsonConfig=(JSONObject) obj.get("ECommerceDeployment");
           if (jsonConfig==null) {
               throw new ECommerceException(String.format("There is no attribute 'ECommerceDeployment' in json file %s",
                       path));
           }
           JSONArray stepArray = (JSONArray) jsonConfig.get("steps");
           if(stepArray==null)
               throw new ECommerceException(String.format("There is no attribute 'steps' in json file %s",
                       path));
           steps=stepArray;
           configuration=obj;
        }
        catch (IOException e) {
            throw new ECommerceException(e.getMessage());
        }

    }
}
