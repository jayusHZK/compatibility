package com.jayus.smallSpring.step02.factory.support;

import com.jayus.smallSpring.step02.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: h zk
 * @Description: 单例bean 注册器默认实现
 * @Date: 2023/3/8 22:55
 * @Version: 1.0
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private final Map<String, Object> singletonObjects = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    protected void addSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName,singletonObject);
    }
}