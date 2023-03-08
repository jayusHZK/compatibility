package com.jayus.smallSpring.step01.test;

import com.jayus.smallSpring.bean.UserService;
import com.jayus.smallSpring.step01.BeanDefinition;
import com.jayus.smallSpring.step01.BeanFactory;

public class Test {

    public static void main(String[] args) {
        // 初始化 beanFactory
        BeanFactory beanFactory = new BeanFactory();

        //注入bean
        BeanDefinition beanDefinition = new BeanDefinition(new UserService());
        beanFactory.registerBeanDefinition("userService",beanDefinition);

        // 获取bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
    }

}
