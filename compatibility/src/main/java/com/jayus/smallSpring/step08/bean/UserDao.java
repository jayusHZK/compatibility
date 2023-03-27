package com.jayus.smallSpring.step08.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : h zk
 * @date : 2023/3/27 16:10
 * @description :
 **/
public class UserDao {

    private static Map<String,String> hashMap = new HashMap<>();

    public void initDataMethod(){
        System.out.println("执行：init-method");
        hashMap.put("1", "a");
        hashMap.put("2", "b");
        hashMap.put("3", "c");
    }

    public void destroyDataMethod(){
        System.out.println("执行：destroy-method");
        hashMap.clear();
    }

    public String queryUserName(String uId) {
        return hashMap.get(uId);
    }

}
