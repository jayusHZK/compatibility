package com.jayus.smallSpring.step12.beans.factory;

import com.jayus.smallSpring.step12.beans.BeansException;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/4/9 18:08
 * @Version: 1.0
 */
public interface BeanFactory {

    Object getBean(String name) throws BeansException;

    Object getBean(String name,Object... args) throws BeansException;

    <T> T getBean(String name,Class<T> requireType) throws BeansException;

}