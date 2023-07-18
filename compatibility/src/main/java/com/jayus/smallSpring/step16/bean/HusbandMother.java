package com.jayus.smallSpring.step16.bean;

import com.jayus.smallSpring.step16.beans.factory.FactoryBean;

import java.lang.reflect.Proxy;

/**
 * @author : h zk
 * @date : 2023/7/18 10:36
 * @description :
 **/
public class HusbandMother implements FactoryBean<IMother> {

    @Override
    public IMother getObject() throws Exception {
        return (IMother) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{IMother.class}
                , (proxy, method, args) -> "婚后媳妇妈妈的职责" + method.getName());
    }

    @Override
    public Class<?> getObjectType() {
        return IMother.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
