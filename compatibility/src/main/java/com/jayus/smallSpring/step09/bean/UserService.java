package com.jayus.smallSpring.step09.bean;

/**
 * @author : h zk
 * @date : 2023/3/29 16:20
 * @description :
 **/
public class UserService {

    private String uid;

    private String company;

    private String location;

    private IUserDao userDao;

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

    public IUserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }
}
