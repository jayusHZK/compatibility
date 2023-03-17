package com.jayus.smallSpring.step07.beans.factory.config;

/**
 * @author : h zk
 * @date : 2023/3/17 14:41
 * @description :
 **/
public class BeanReference {

    private final String beanName;

    public BeanReference(String name) {
        this.beanName = name;
    }

    public String getBeanName() {
        return beanName;
    }

}
