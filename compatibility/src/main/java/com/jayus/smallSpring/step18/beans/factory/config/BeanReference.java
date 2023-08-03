package com.jayus.smallSpring.step18.beans.factory.config;

/**
 * @author : h zk
 * @date : 2023/8/3 18:22
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
