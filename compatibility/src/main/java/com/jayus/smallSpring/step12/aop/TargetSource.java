package com.jayus.smallSpring.step12.aop;

/**
 * @author : h zk
 * @date : 2023/4/7 15:06
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
