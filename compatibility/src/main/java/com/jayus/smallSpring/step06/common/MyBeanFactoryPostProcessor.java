package com.jayus.smallSpring.step06.common;

import com.jayus.smallSpring.step06.beans.BeansException;
import com.jayus.smallSpring.step06.beans.PropertyValue;
import com.jayus.smallSpring.step06.beans.PropertyValues;
import com.jayus.smallSpring.step06.beans.factory.ConfigurableListableBeanFactory;
import com.jayus.smallSpring.step06.beans.factory.config.BeanDefinition;
import com.jayus.smallSpring.step06.beans.factory.config.BeanFactoryPostProcessor;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/3/14 23:23
 * @Version: 1.0
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("company","改为：字节跳动"));
    }
}