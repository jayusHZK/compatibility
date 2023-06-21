package com.jayus.smallSpring.step14.beans.factory.config;

/**
 * @author : h zk
 * @date : 2023/6/21 15:02
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
