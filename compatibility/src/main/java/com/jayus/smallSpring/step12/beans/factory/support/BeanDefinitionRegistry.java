package com.jayus.smallSpring.step12.beans.factory.support;

import com.jayus.smallSpring.step12.beans.BeansException;
import com.jayus.smallSpring.step12.beans.factory.config.BeanDefinition;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/4/9 23:24
 * @Version: 1.0
 */
public interface BeanDefinitionRegistry {

    /**
     *  向注册表中注册 BeanDefinition
     * @param beanName
     * @param beanDefinition
     */
    void registryBeanDefinition(String beanName, BeanDefinition beanDefinition);

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
     * @return
     */
    String[] getBeanDefinitionNames();

}
