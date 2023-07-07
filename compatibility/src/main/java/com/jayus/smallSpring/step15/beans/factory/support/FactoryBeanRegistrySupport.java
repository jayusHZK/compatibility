package com.jayus.smallSpring.step15.beans.factory.support;

import com.jayus.smallSpring.step15.beans.BeansException;
import com.jayus.smallSpring.step15.beans.factory.FactoryBean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : h zk
 * @date : 2023/7/6 18:18
 * @description :
 **/
public abstract class FactoryBeanRegistrySupport extends DefaultSingletonBeanRegistry {

    private final Map<String, Object> factoryBeanObjectCache = new ConcurrentHashMap<>();

    protected Object getCachedObjectForFactoryBean(String beanName) {
        Object object = this.factoryBeanObjectCache.get(beanName);
        return object != NULL_OBJECT ? object : null;
    }

    protected Object getObjectFromFactoryBean(FactoryBean factory,String beanName) {
        if (factory.isSingleton()){
            Object object = this.factoryBeanObjectCache.get(beanName);
            if (object == null){
                object = doGetOjbectFromFactoryBean(factory,beanName);
                this.factoryBeanObjectCache.put(beanName,(object != null?object:NULL_OBJECT));
            }
            return object != NULL_OBJECT ? object:null;
        }else {
            return doGetOjbectFromFactoryBean(factory,beanName);
        }

    }

    private Object doGetOjbectFromFactoryBean(final FactoryBean factory,final String beanName){
        try {
            return factory.getObject();
        } catch (Exception e) {
            throw new BeansException("FactoryBean threw exception on object[" + beanName + "] creation", e);
        }
    }

}
