package com.jayus.jsonUtilTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

/**
 * @author : h zk
 * @date : 2022/6/28 14:36
 * @description :
 **/
public class asdf {
    public static void main(String[] args) {
        String a = "[{name:\"男\",value:\"35\"},{name:\"女\",value:\"65\"}]";
        //System.out.println(a);
        Gson gson = new Gson();
        JsonArray asJsonArray = JsonParser.parseString(a).getAsJsonArray();
        System.out.println(asJsonArray.get(0).getAsJsonObject().get("value"));

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(a);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(jsonNode);
    }
}
