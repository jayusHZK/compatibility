package com.jayus.smallSpring.step08.beans.factory.config;

/**
 * @author : h zk
 * @date : 2023/3/23 19:39
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
