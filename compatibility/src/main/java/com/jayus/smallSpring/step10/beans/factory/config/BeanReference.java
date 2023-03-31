package com.jayus.smallSpring.step10.beans.factory.config;

/**
 * @author : h zk
 * @date : 2023/3/31 15:52
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
