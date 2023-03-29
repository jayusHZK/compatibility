package com.jayus.smallSpring.step09.test;

import com.jayus.smallSpring.step09.bean.UserService;
import com.jayus.smallSpring.step09.context.support.ClassPathXmlApplicationContext;
import com.jayus.vo.UserVO;

/**
 * @author : h zk
 * @date : 2023/3/29 16:23
 * @description :
 **/
public class Test {

    public static void main(String[] args) {
        test_prototype();
    }

    public static void set(UserVO userVO){
        userVO.setAge(1);
    }

    public static void test_prototype(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();
        UserService userService1 = applicationContext.getBean("userService", UserService.class);
        UserService userService2 = applicationContext.getBean("userService", UserService.class);

        System.out.println(userService1);
        System.out.println(userService2);

        System.out.println(userService1.queryUserInfo());
    }

}

