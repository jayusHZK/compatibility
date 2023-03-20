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
        // 执行好一切初始化工作，bean的初始化和依赖注入
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        // 创建 jvm 关闭钩子函数
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