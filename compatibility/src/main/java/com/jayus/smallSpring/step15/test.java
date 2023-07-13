package com.jayus.smallSpring.step15;

import com.jayus.smallSpring.step15.bean.IUserService;
import com.jayus.smallSpring.step15.context.support.ClassPathXmlApplicationContext;

/**
 * @author : h zk
 * @date : 2023/7/13 18:21
 * @description :
 **/
public class test {

    public static void main(String[] args) {
        test_autoProxy();
    }

    public static void test_autoProxy(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        IUserService userService = applicationContext.getBean("userService", IUserService.class);
        System.out.println(userService.queryUserInfo());
    }

}
