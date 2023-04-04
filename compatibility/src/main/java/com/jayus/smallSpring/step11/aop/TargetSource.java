package com.jayus.smallSpring.step11.aop;

/**
 * @author : h zk
 * @date : 2023/4/4 11:50
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
