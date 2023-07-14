package com.jayus.smallSpring.step16.beans.factory;

import com.jayus.smallSpring.step16.beans.BeansException;

import java.util.Map;

/**
 * @author : h zk
 * @date : 2023/7/14 11:28
 * @description :
 **/
public interface ListableBeanFactory extends BeanFactory{

    <T> Map<String,T> getBeansOfType(Class<T> type) throws BeansException;

    String[] getBeanDefinitionNames();

}
