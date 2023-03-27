package com.jayus.smallSpring.step08.test;

import com.jayus.smallSpring.step08.bean.UserService;
import com.jayus.smallSpring.step08.context.support.ClassPathXmlApplicationContext;

/**
 * @author : h zk
 * @date : 2023/3/27 16:20
 * @description :
 **/
public class Test {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();
        UserService userService = applicationContext.getBean("userService", UserService.class);
        System.out.println(userService.queryUserInfo());

        System.out.println("ApplicationContextAware："+userService.getApplicationContext());
        System.out.println("BeanFactoryAware："+userService.getBeanFactory());
    }

}
