package com.jayus.smallSpring.step14.aop;

/**
 * @author : h zk
 * @date : 2023/6/21 10:00
 * @description :
 **/
public class TargetSource {

    private final Object target;

    public TargetSource(Object target) {
        this.target = target;
    }

    public Class<?>[] getTargetClass(){
        return this.target.getClass().getInterfaces();
    }

    public Object getTarget() {
        return target;
    }

}
