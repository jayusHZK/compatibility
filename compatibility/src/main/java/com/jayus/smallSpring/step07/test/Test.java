package com.jayus.smallSpring.step07.test;

import com.jayus.smallSpring.step07.bean.UserService;
import com.jayus.smallSpring.step07.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/3/19 22:25
 * @Version: 1.0
 */
public class Test {

    public static void main(String[] args) {
        test_xml();
        test_hook();
    }

    public static void test_xml() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registShutdownHook();

        UserService userService = applicationContext.getBean("userService", UserService.class);
        System.out.println(userService.queryUserInfo());
    }

    public static void test_hook(){
        Runtime.getRuntime().addShutdownHook(new Thread(() ->{
            System.out.println("close!");
        }));
    }

}