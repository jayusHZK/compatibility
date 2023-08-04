package com.jayus.smallSpring.step18.beans.factory.support;

import com.jayus.smallSpring.step18.beans.BeansException;
import com.jayus.smallSpring.step18.beans.factory.DisposableBean;
import com.jayus.smallSpring.step18.beans.factory.ObjectFactory;
import com.jayus.smallSpring.step18.beans.factory.config.SingletonRegistry;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : h zk
 * @date : 2023/8/4 10:47
 * @description :
 **/
public class DefaultSingletonBeanRegistry implements SingletonRegistry {

    protected static final Object NULL_OBJECT = new Object();

    // 一级缓存 普通对象
    private Map<String,Object> singletonObjects = new ConcurrentHashMap<>();

    // 二级缓存，提前暴露对象，没有完全实例化的对象
    protected final Map<String,Object> earlySingletonObjects = new HashMap<>();

    // 三级缓存 存放代理对象
    private final Map<String, ObjectFactory<?>> singletonFactories = new HashMap<>();

    private final Map<String, DisposableBean> disposableBeans = new LinkedHashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        Object singletonObject = singletonObjects.get(beanName);
        if (null == singletonObject){
            singletonObject = earlySingletonObjects.get(beanName);
            if (null == singletonObject){
                ObjectFactory<?> singletonFactory = singletonFactories.get(beanName);
                if (singletonFactory != null){
                    singletonObject = singletonFactory.getObject();
                    earlySingletonObjects.put(beanName,singletonObject);
                    singletonFactories.remove(beanName);
                }
            }
        }
        return singletonObject;
    }

    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName,singletonObject);
        earlySingletonObjects.remove(beanName);
        singletonFactories.remove(beanName);
    }

    protected void addSingletonFactory(String beanName,ObjectFactory<?> singletonFactory){
        if (!this.singletonObjects.containsKey(beanName)){
            this.singletonFactories.put(beanName,singletonFactory);
            this.earlySingletonObjects.remove(beanName);
        }
    }

    public void registerDisposableBean(String beanName,DisposableBean bean){
        disposableBeans.put(beanName,bean);
    }

    public void destroySingletons(){
        Set<String> keySet = this.disposableBeans.keySet();
        Object[] disposableBeanNames = keySet.toArray();

        for (int i = disposableBeanNames.length -1; i >= 0 ; i--) {
            Object beanName = disposableBeanNames[i];
            DisposableBean disposableBean = disposableBeans.get(beanName);
            try {
                disposableBean.destroy();
            } catch (Exception e) {
                throw new BeansException("Destroy method on bean with name '" + beanName + "' threw an exception", e);
            }
        }
    }

}
