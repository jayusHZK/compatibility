package com.jayus.smallSpring.step15.beans.factory.config;

/**
 * @author : h zk
 * @date : 2023/7/6 11:41
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
