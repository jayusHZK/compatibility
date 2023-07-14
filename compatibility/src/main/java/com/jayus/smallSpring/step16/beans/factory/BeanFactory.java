package com.jayus.smallSpring.step16.beans.factory;

import com.jayus.smallSpring.step16.beans.BeansException;

/**
 * @author : h zk
 * @date : 2023/7/14 11:21
 * @description :
 **/
public interface BeanFactory {

    Object getBean(String name) throws BeansException;

    Object getBean(String name,Object... args) throws BeansException;

    <T> T getBean(String name,Class<T> requiredType) throws BeansException;

    <T> T getBean(Class<T> requiredType) throws BeansException;

}
