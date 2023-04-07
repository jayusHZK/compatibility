package com.jayus.smallSpring.step12.aop.framework;

import com.jayus.smallSpring.step12.aop.AdvisedSupport;

/**
 * @author : h zk
 * @date : 2023/4/7 15:24
 * @description :
 **/
public class ProxyFactory {

    private AdvisedSupport advisedSupport;

    public ProxyFactory(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    private AopProxy createAopProxy(){
        if (advisedSupport.isProxyTargetClass()){
            return null;
        }
        return null;
    }

}
