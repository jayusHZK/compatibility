package com.jayus.smallSpring.step03.factory.config;

/**
 * @Author: h zk
 * @Description: bean 定义
 * @Date: 2023/3/10 00:13
 * @Version: 1.0
 */
public class BeanDefinition {

    private Class beanClass;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }
}