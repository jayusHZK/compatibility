package com.jayus.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jayus.smallSpring.step17.beans.BeansException;
import com.jayus.smallSpring.step17.beans.factory.BeanFactory;
import com.jayus.smallSpring.step17.beans.factory.BeanFactoryAware;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author : h zk
 * @date : 2022/6/21 10:56
 * @description :
 **/
@Data
public class UserVO implements BeanFactoryAware {

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

    }

    public UserVO() {
    }

    public UserVO(String username) {
        this.username = username;
    }

    private String username;

    private String password;

    private Integer age;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date date;

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

    public Integer setString(String s){
        return 1;
    }

    public boolean isOld(Integer i){
        return true;
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
