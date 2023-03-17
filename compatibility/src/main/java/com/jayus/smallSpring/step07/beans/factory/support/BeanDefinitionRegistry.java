package com.jayus.smallSpring.step07.beans.factory.support;

import com.jayus.smallSpring.step07.beans.BeansException;
import com.jayus.smallSpring.step07.beans.factory.config.BeanDefinition;

/**
 * @author : h zk
 * @date : 2023/3/17 15:11
 * @description :
 **/
public interface BeanDefinitionRegistry {

    /**
     * 向注册表中注册 BeanDefinition
     * @param beanName
     * @param beanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    /**
     * 使用 Bean 名称查询 BeanDefinition
     * @param beanName
     * @return
     * @throws BeansException
     */
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 判断是否包含指定名称的 BeanDefinition
     * @param beanName
     * @return
     */
    boolean containsBeanDefinition(String beanName);

    /**
     * 返回注册表中所有的 Bean 名称
     */
    String[] getBeanDefinitionNames();

}
