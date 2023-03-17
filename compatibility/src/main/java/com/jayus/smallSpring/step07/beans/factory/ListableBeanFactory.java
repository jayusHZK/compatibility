package com.jayus.smallSpring.step07.beans.factory;

import com.jayus.smallSpring.step07.beans.BeansException;

import java.util.Map;

/**
 * @author : h zk
 * @date : 2023/3/17 14:26
 * @description :
 **/
public interface ListableBeanFactory extends BeanFactory{

    /**
     *  按照类型返回 Bean 实例
     * @param type
     * @param <T>
     * @return
     * @throws BeansException
     */
    <T> Map<String,T> getBeansOfType(Class<T> type) throws BeansException;

    /**
     * 返回注册表中所有 Bean 名称
     * @return
     */
    String[] getBeanDefinitionNames();

}
