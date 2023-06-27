package com.jayus.smallSpring.step14;

import com.jayus.smallSpring.step14.bean.IUserService;
import com.jayus.smallSpring.step14.context.support.ClassPathXmlApplicationContext;

/**
 * @author : h zk
 * @date : 2023/6/27 14:47
 * @description :
 **/
public class test {

    public static void main(String[] args) {
        test_scan();
    }

    public static void test_scan(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        IUserService userService = applicationContext.getBean("userService", IUserService.class);
        System.out.println(userService.queryUserInfo());
    }

}
