package com.jayus;

import com.google.gson.*;
import com.jayus.vo.UserVO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class gson {

    @Test
    void contextLoads() {

    }

    public static void main(String[] args) {
        String a = "{\\\"ip\\\":\\\"183.11.130.226\\\",\\\"sim_area\\\":\\\"\\\",\\\"andriodNum\\\":\\\"d8e7b791b2d314ad\\\",\\\"app_name\\\":\\\"com.spotifyproxy.fast.test\\\",\\\"mobileNetWork\\\":\\\"\\\",\\\"firstOpen\\\":1664156202319,\\\"adNumber\\\":\\\"7a976a08-594b-49b6-a34d-998fd80ca314\\\",\\\"versions\\\":1,\\\"productLanguage\\\":\\\"zh\\\",\\\"state_name\\\":\\\"CN\\\",\\\"domain\\\":\\\"api.uyznc.top\\\",\\\"sdkNum\\\":30,\\\"projectId\\\":231,\\\"current_time\\\":1664156817380}";
        JsonElement jsonElement1 = JsonParser.parseString(a);
        JsonObject asJsonObject2 = jsonElement1.getAsJsonObject();
        System.out.println(asJsonObject2);

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
        // 对象转jsonstring
        Gson gson = new Gson();
        String userVOJson = gson.toJson(userVO);
        System.out.println(userVOJson);
        //jsonstring 转对象
        UserVO userVO2 = gson.fromJson(userVOJson, UserVO.class);
        System.out.println(userVO2);
        //string转json对象
        JsonElement jsonElement = JsonParser.parseString(userVOJson);
        JsonObject asJsonObject = jsonElement.getAsJsonObject();
        System.out.println(asJsonObject.get("username").getAsString());
        JsonArray friendList = asJsonObject.getAsJsonArray("friendList");
        JsonObject asJsonObject1 = friendList.get(0).getAsJsonObject();
        System.out.println(asJsonObject1.get("username").getAsString());
    }
}
