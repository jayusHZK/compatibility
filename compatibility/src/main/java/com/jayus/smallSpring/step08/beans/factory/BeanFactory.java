package com.jayus.smallSpring.step08.beans.factory;

import com.jayus.smallSpring.step08.beans.BeansException;

/**
 * @author : h zk
 * @date : 2023/3/23 19:22
 * @description :
 **/
public interface BeanFactory {

    Object getBean(String name) throws BeansException;

    Object getBean(String name,Object... args) throws BeansException;

    <T> T getBean(String name,Class<T> requireType) throws BeansException;

}
