package com.jayus.smallSpring.step05.beans.factory.config;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/3/12 20:39
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