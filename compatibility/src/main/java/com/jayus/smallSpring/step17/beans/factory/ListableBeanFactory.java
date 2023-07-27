package com.jayus.smallSpring.step17.beans.factory;

import com.jayus.smallSpring.step17.beans.BeansException;

import java.util.Map;

/**
 * @author : h zk
 * @date : 2023/7/27 11:21
 * @description :
 **/
public interface ListableBeanFactory extends BeanFactory {

    <T>Map<String,T> getBeansOfType(Class<T> type) throws BeansException;

    String[] getBeanDefinitionNames();

}
