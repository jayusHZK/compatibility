package com.jayus.smallSpring.step09.bean;

import com.jayus.smallSpring.step09.beans.BeansException;
import com.jayus.smallSpring.step09.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : h zk
 * @date : 2023/3/29 16:17
 * @description :
 **/
public class ProxyBeanFactory implements FactoryBean<IUserDao> {

    @Override
    public IUserDao getObject() throws BeansException {
        InvocationHandler handler = (proxy,method,args) -> {
          if ("toString".equals(method.getName())) return this.toString();
            Map<String, String> hashMap = new HashMap<>();
            hashMap.put("1", "a");
            hashMap.put("2", "b");
            hashMap.put("3", "c");

            return "你被代理了 " + method.getName() + "：" + hashMap.get(args[0].toString());
        };
        return (IUserDao) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),new Class[]{IUserDao.class},handler);
    }

    @Override
    public Class<?> getObjectType() {
        return IUserDao.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
