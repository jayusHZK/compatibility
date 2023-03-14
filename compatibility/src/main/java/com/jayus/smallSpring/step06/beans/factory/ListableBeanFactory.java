package com.jayus.smallSpring.step06.beans.factory;

import com.jayus.smallSpring.step06.beans.BeansException;

import java.util.Map;

/**
 * @author : h zk
 * @date : 2023/3/14 17:27
 * @description :
 **/
public interface ListableBeanFactory extends BeanFactory{

    /**
     * 按照类型返回Beanh实例
     * @param type
     * @param <T>
     * @return
     * @throws BeansException
     */
    <T>Map<String,T> getBeanOfType(Class<T> type) throws BeansException;

    /**
     * 返回注册表中所有的Bean名称
     * @return
     */
    String[] getBeanDefinitionNames();

}
