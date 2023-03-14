package com.jayus.smallSpring.step06.beans.factory.config;

/**
 * @author : h zk
 * @date : 2023/3/14 17:31
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
