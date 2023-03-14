package com.jayus.smallSpring.step05.beans.factory;

import com.jayus.smallSpring.step05.beans.BeansException;

/**
 * @Author: h zk
 * @Description: 顶级bean工厂
 * @Date: 2023/3/12 19:42
 * @Version: 1.0
 */
public interface BeanFactory {

    Object getBean(String name) throws BeansException;

    Object getBean(String name,Object... args);

    <T> T getBean(String name,Class<T> requiredType) throws BeansException;

}
