package com.jayus.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : h zk
 * @date : 2022/6/21 10:56
 * @description :
 **/
public class UserVO{

    public UserVO() {
    }

    public UserVO(String username) {
        this.username = username;
    }

    private String username;

    private String password;

    private Integer age;

    private List<UserVO> friendList = new ArrayList<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<UserVO> getFriendList() {
        return friendList;
    }

    public void setFriendList(List<UserVO> friendList) {
        this.friendList = friendList;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", friendList=" + friendList +
                '}';
    }
}
