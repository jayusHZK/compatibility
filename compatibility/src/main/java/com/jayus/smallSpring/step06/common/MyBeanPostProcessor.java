package com.jayus.smallSpring.step06.common;

import com.jayus.smallSpring.step06.bean.UserService;
import com.jayus.smallSpring.step06.beans.BeansException;
import com.jayus.smallSpring.step06.beans.factory.config.BeanPostProcessor;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/3/14 23:21
 * @Version: 1.0
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("userService".equals(beanName)){
            UserService userService = (UserService) bean;
            userService.setLocation("改为：北京");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}