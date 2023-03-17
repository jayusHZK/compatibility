package com.jayus.smallSpring.step07.beans.factory;

import com.jayus.smallSpring.step07.beans.BeansException;

/**
 * @author : h zk
 * @date : 2023/3/17 14:06
 * @description :
 **/
public interface BeanFactory {

    Object getBean(String name) throws BeansException;

    Object getBean(String name,Object... args) throws BeansException;

    <T> T getBean(String name,Class<T> requireType) throws BeansException;

}
