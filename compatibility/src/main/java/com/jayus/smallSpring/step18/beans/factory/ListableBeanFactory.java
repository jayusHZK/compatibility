package com.jayus.smallSpring.step18.beans.factory;

import com.jayus.smallSpring.step18.beans.BeansException;

import java.util.Map;

/**
 * @author : h zk
 * @date : 2023/8/2 17:33
 * @description :
 **/
public interface ListableBeanFactory extends BeanFactory {

    <T> Map<String,T> getBeansOfType(Class<T> type) throws BeansException;

    String[] getBeanDefinitionNames();

}
