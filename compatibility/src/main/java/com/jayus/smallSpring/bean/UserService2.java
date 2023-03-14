package com.jayus.smallSpring.bean;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/3/14 23:16
 * @Version: 1.0
 */
public class UserService2 {
    private String name;

    public UserService2() {
    }

    public UserService2(String name) {
        this.name = name;
    }

    public void queryUserInfo() {
        System.out.println("查询用户信息：" + name);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("");
        sb.append("").append(name);
        return sb.toString();
    }
}