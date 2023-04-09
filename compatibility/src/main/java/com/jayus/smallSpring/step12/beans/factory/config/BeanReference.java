package com.jayus.smallSpring.step12.beans.factory.config;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/4/9 19:17
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