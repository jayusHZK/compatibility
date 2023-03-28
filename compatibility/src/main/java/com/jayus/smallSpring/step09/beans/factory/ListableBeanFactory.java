package com.jayus.smallSpring.step09.beans.factory;

import com.jayus.smallSpring.step09.beans.BeansException;

import java.util.Map;

/**
 * @author : h zk
 * @date : 2023/3/28 11:04
 * @description :
 **/
public interface ListableBeanFactory extends BeanFactory{

    /**
     * 按照类型返回 Bean 实例
     * @param type
     * @param <T>
     * @return
     * @throws BeansException
     */
    <T>Map<String,T> getBeansOfType(Class<T> type) throws BeansException;

    /**
     * 返回注册表中所有的 Bean 名称
     * @return
     */
    String[] getBeanDefinitionNames();

}
