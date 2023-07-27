package com.jayus.smallSpring.step17.beans.factory.config;

/**
 * @author : h zk
 * @date : 2023/7/27 18:21
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
