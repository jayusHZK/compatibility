package com.jayus.smallSpring.step15.beans.factory;

import com.jayus.smallSpring.step15.beans.BeansException;

/**
 * @author : h zk
 * @date : 2023/7/5 18:24
 * @description :
 **/
public interface BeanFactory {

    Object getBean(String name) throws BeansException;

    Object getBean(String name,Object... args) throws BeansException;

    <T> T getBean(String name,Class<T> requiredType) throws BeansException;

    <T> T getBean(Class<T> requiredType) throws BeansException;

}
