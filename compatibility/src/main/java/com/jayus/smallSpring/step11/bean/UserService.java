package com.jayus.smallSpring.step11.bean;

import java.util.Random;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/4/6 23:14
 * @Version: 1.0
 */
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