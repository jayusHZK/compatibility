package com.jayus.smallSpring.step09.beans.factory;

import com.jayus.smallSpring.step09.beans.BeansException;

/**
 * @author : h zk
 * @date : 2023/3/28 10:42
 * @description :
 **/
public interface BeanFactory {

    Object getBean(String name) throws BeansException;

    Object getBean(String name,Object... args) throws BeansException;

    <T> T getBean(String name,Class<T> requiredType) throws BeansException;

}
