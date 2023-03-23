package com.jayus.smallSpring.step08.beans.factory;

import com.jayus.smallSpring.step08.beans.BeansException;

import java.util.Map;

/**
 * @author : h zk
 * @date : 2023/3/23 19:33
 * @description :
 **/
public interface ListableBeanFactory extends BeanFactory{

    /**
     * 按照类型返回 bean 实例
     * @param type
     * @param <T>
     * @return
     * @throws BeansException
     */
    <T>Map<String,T> getBeanOfType(Class<T> type) throws BeansException;

    String[] getBeanDefinitionNames();

}
