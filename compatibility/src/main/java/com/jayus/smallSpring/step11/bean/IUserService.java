package com.jayus.smallSpring.step11.bean;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/4/6 23:12
 * @Version: 1.0
 */
public interface IUserService {

    String queryUserInfo();

    String register(String userName);

}