package com.jayus.smallSpring.step04.Test;

import com.jayus.smallSpring.step04.bean.UserDao;
import com.jayus.smallSpring.step04.bean.UserService;
import com.jayus.smallSpring.step04.PropertyValue;
import com.jayus.smallSpring.step04.PropertyValues;
import com.jayus.smallSpring.step04.factory.config.BeanDefinition;
import com.jayus.smallSpring.step04.factory.config.BeanReference;
import com.jayus.smallSpring.step04.factory.support.DefaultListableBeanFactory;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/3/12 16:34
 * @Version: 1.0
 */
public class Test {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.registerBeanDefinition("userDao",new BeanDefinition(UserDao.class));

        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uid","2"));
        propertyValues.addPropertyValue(new PropertyValue("userDao",new BeanReference("userDao")));

        BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);
        beanFactory.registerBeanDefinition("userService",beanDefinition);

        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
    }

}