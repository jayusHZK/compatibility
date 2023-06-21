package com.jayus.smallSpring.step14.beans.factory;

import com.jayus.smallSpring.step14.beans.BeansException;

import java.util.Map;

/**
 * @author : h zk
 * @date : 2023/6/21 14:21
 * @description :
 **/
public interface ListableBeanFactory extends BeanFactory{

    <T> Map<String,T> getBeanOfType(Class<T> type) throws BeansException;

    String[] getBeanDefinitionNames();

}
