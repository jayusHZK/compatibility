package com.jayus.smallSpring.step16.aop.framework;

import com.jayus.smallSpring.step16.aop.AdvisedSupport;

/**
 * @author : h zk
 * @date : 2023/7/17 18:04
 * @description :
 **/
public class ProxyFactory {

    private AdvisedSupport advisedSupport;

    public ProxyFactory(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    public Object getProxy(){
        return createAopProxy().getProxy();
    }

    public AdvisedSupport getAdvisedSupport() {
        return advisedSupport;
    }

    private AopProxy createAopProxy(){
        if (advisedSupport.isProxyTargetClass()){
            return new Cglib2AopProxy(advisedSupport);
        }
        return new JdkDynamicAopProxy(advisedSupport);
    }
}
