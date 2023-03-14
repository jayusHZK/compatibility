package com.jayus.smallSpring.step06.bean;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/3/14 23:20
 * @Version: 1.0
 */
public class UserService {

    private String uid;

    private String company;

    private String location;

    private UserDao userDao;

    public String queryUserInfo(){
        return userDao.queryUserName(uid) + "," + company + "," + location;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}