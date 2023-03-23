package com.jayus.smallSpring.step08.beans.factory.support;

import com.jayus.smallSpring.step08.beans.BeansException;
import com.jayus.smallSpring.step08.beans.factory.DisposableBean;
import com.jayus.smallSpring.step08.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/3/23 23:29
 * @Version: 1.0
 */
public class DefaultSingletonBeanRegist implements SingletonBeanRegistry {

    private Map<String, Object> singletonObjects = new HashMap<>();

    private final Map<String, DisposableBean> disposableBeans = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    public void registerDisposableBean(String beanName, DisposableBean disposableBean) {
        disposableBeans.put(beanName, disposableBean);
    }

    public void destorySingletons() {
        Set<String> keySet = this.disposableBeans.keySet();
        Object[] disposableBeanNames = keySet.toArray();

        for (int i = disposableBeanNames.length -1; i >= 0; i--) {
            Object beanName = disposableBeanNames[i];
            DisposableBean disposableBean = disposableBeans.remove(beanName);
            try {
                disposableBean.destroy();
            } catch (Exception e) {
                throw new BeansException("Destroy method on bean with name '" + beanName + "' threw an exception", e);
            }
        }
    }

}