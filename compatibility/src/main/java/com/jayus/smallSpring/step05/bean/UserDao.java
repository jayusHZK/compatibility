package com.jayus.smallSpring.step05.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/3/12 16:31
 * @Version: 1.0
 */
public class UserDao {

    private static Map<String,String> hashMap = new HashMap<>();

    static {
        hashMap.put("1","a");
        hashMap.put("2","b");
        hashMap.put("3","c");

    }

    public String queryUserName(String uid){
        return hashMap.get(uid);
    }

}