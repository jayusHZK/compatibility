package com.jayus.smallSpring.step12.bean;

import java.util.Random;

/**
 * @author : h zk
 * @date : 2023/4/12 15:31
 * @description :
 **/
public class UserService implements IUserService {

    @Override
    public String queryUserInfo() {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "小傅哥，100001，深圳";
    }

    @Override
    public String register(String userName) {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "注册用户：" + userName + " success！";
    }
}
