package com.jayus.smallSpring.step04.factory;

import com.jayus.smallSpring.step04.BeansException;

/**
 * @Author: h zk
 * @Description: bean工厂顶级接口
 * @Date: 2023/3/12 13:42
 * @Version: 1.0
 */
public interface BeanFactory {

    Object getBean(String name) throws BeansException;

    Object getBean(String name,Object... args) throws BeansException;

}