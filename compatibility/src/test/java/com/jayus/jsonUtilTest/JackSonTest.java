package com.jayus.jsonUtilTest;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayus.vo.UserVO;

import java.io.IOException;

/**
 * @author : h zk
 * @date : 2022/6/21 11:24
 * @description :
 **/
public class JackSonTest {
    public static void main(String[] args) {
        UserVO userVO = new UserVO();
        userVO.setUsername("xiaoming");
        userVO.setPassword("123456");
        userVO.setAge(20);
        UserVO userVO1 = new UserVO();
        userVO1.setUsername("xiaofang");
        userVO1.setPassword("123456");
        userVO1.setAge(18);
        userVO.getFriendList().add(userVO1);
        //userVO1.getFriendList().add(userVO);
        ObjectMapper objectMapper = new ObjectMapper();
        String userVOJson = "";
        try {
            userVOJson = objectMapper.writeValueAsString(userVO);
            System.out.println(userVOJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        UserVO jsonToUserVO = null;
        try {
            jsonToUserVO = objectMapper.readValue(userVOJson,userVO.getClass());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(jsonToUserVO);
        JsonParser parser = null;
        JsonNode treeNode = null;
        try {
            parser = objectMapper.createParser(userVOJson);
            treeNode = objectMapper.readTree(parser);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JsonNode friendList = treeNode.get("friendList");
        System.out.println(friendList.get(0).get("username").asText());
        System.out.println();
    }
}