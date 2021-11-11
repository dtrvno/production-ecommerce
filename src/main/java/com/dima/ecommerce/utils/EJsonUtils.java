package com.dima.ecommerce.utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
}
