package com.jayus.smallSpring.step09.beans.factory.config;

/**
 * @author : h zk
 * @date : 2023/3/28 11:21
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
