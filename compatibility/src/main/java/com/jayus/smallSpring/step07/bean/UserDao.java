package com.jayus.smallSpring.step07.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/3/18 18:24
 * @Version: 1.0
 */
public class UserDao {

    private static Map<String, String> hashMap = new HashMap<>();

    public void initDataMethod() {
        System.out.println("执行：init-method");
        hashMap.put("1","a");
        hashMap.put("2","b");
        hashMap.put("3","c");
    }

    public void destroyDataMethod(){
        System.out.println("执行：destroy-method");
        hashMap.clear();
    }

    public String queryUserName(String uid){
        return hashMap.get(uid);
    }

}