package com.jayus.smallSpring.step16.beans.factory.config;

/**
 * @author : h zk
 * @date : 2023/7/14 11:49
 * @description :
 **/
public class BeanReference {

    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
