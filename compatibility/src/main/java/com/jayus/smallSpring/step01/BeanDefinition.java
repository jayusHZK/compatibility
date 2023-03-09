package com.jayus.smallSpring.step01;

import com.jayus.onjava.thirteen.seriorFunction.O;

public class BeanDefinition {

    private Object bean;

    public BeanDefinition(Object bean) {
        this.bean = bean;
    }

    public Object getBean(){
        return bean;
    }
}
