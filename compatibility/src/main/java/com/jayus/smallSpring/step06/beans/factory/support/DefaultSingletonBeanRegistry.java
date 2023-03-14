package com.jayus.smallSpring.step06.beans.factory.support;

import com.jayus.smallSpring.step06.beans.factory.config.SingletonBeanRegistrt;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : h zk
 * @date : 2023/3/14 18:19
 * @description :
 **/
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistrt {

    private Map<String,Object> singletonObjects = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    public void addSingleton(String beanName,Object singletonObject){
        singletonObjects.put(beanName,singletonObject);
    }
}
