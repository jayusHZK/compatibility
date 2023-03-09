package com.jayus.smallSpring.step02.test;

import com.jayus.smallSpring.bean.UserService;
import com.jayus.smallSpring.step02.factory.config.BeanDefinition;
import com.jayus.smallSpring.step02.factory.support.DefaultListableBeanFactory;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/3/8 23:19
 * @Version: 1.0
 */
public class Test {
    public static void main(String[] args) {
        // 初始化 beanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 注册bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService",beanDefinition);

        // 第一次获取bean  第一次获取会将其放入单例容器中
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();

        // 第二次获取 从单例容器获取
        UserService userService_singleton = (UserService) beanFactory.getSingleton("userService");
        userService_singleton.queryUserInfo();
    }
}