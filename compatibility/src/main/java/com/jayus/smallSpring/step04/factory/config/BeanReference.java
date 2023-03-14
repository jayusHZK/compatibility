package com.jayus.smallSpring.step04.factory.config;

/**
 * @Author: h zk
 * @Description: bean 引用
 * @Date: 2023/3/12 13:47
 * @Version: 1.0
 */
public class BeanReference {

    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}