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
    public static void loadConfiguration(String path) throws  ECommerceException {
        try {
           String content = Files.readString(Paths.get(path), StandardCharsets.US_ASCII);
           JSONObject obj = EJsonUtils.parse(content);
           configuration=(JSONObject) obj.get("ECommerceDeployment");
           if (configuration==null) {
               throw new ECommerceException(String.format("There is no attribute 'ECommerceDeployment' in json file %s",
                       path));
           }
           JSONArray stepArray = (JSONArray) configuration.get("steps");
           if(stepArray==null)
               throw new ECommerceException(String.format("There is no attribute 'steps' in json file %s",
                       path));
           steps=stepArray;
        }
        catch (IOException e) {
            throw new ECommerceException(e.getMessage());
        }

    }
}
