package com.dima.ecommerce.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileWriter;
import java.io.IOException;

public class EJsonUtils {
    public static JSONObject parse(String s) throws ECommerceException {
        try {
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(s);
            return obj;
        } catch (ParseException e) {
            throw new ECommerceException(e.getMessage());
        }
    }
    public static void jsonToFile(JSONObject obj,String fileName)  {
        FileWriter wrt=null;
        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT );
        try {

            String s = obj.toString();
            Object jsonObject = mapper.readValue(s, Object.class);

            String prettyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
            wrt = new FileWriter(fileName);
            wrt.write(prettyJson);
        }
        catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        finally {
            try {
                wrt.flush();
                wrt.close();
            }
            catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }
}
