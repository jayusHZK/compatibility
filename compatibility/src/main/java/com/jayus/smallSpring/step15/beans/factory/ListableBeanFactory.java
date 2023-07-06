package com.jayus.smallSpring.step15.beans.factory;

import com.jayus.smallSpring.step15.beans.BeansException;

import java.util.Map;

/**
 * @author : h zk
 * @date : 2023/7/6 9:45
 * @description :
 **/
public interface ListableBeanFactory extends BeanFactory{

    <T>Map<String,T> getBeansOfType(Class<T> type) throws BeansException;

    String[] getBeanDefinitionNames();

}
