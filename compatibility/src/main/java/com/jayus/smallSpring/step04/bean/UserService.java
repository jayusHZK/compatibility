package com.jayus.smallSpring.step04.bean;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/3/12 16:32
 * @Version: 1.0
 */
public class UserService {

    private String uid;

    private UserDao userDao;

    public void queryUserInfo(){
        System.out.println("查询用户信息：" + userDao.queryUserName(uid));
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}