package com.jayus.smallSpring.step14.bean;

import com.jayus.smallSpring.step14.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : h zk
 * @date : 2023/6/27 14:04
 * @description :
 **/
@Component
public class UserDao {

    private static Map<String,String> hashMap = new HashMap<>();

    static {
        hashMap.put("10001", "abc");
        hashMap.put("10002", "def");
        hashMap.put("10003", "ghi");
    }

    public String queryUserName(String uId){
        return hashMap.get(uId);
    }
}
