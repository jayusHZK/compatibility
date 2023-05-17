package com.jayus.smallSpring.step13.aop.framework;

import com.jayus.smallSpring.step13.aop.AdvisedSupport;

public class Cglib2AopProxy implements AopProxy {

    private final AdvisedSupport advised;

    public Cglib2AopProxy(AdvisedSupport advised) {
        this.advised = advised;
    }

    @Override
    public Object getProxy() {
        return null;
    }


}
