package com.jayus.smallSpring.step12.beans.factory;

import com.jayus.smallSpring.step12.beans.BeansException;

import java.util.Map;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/4/9 18:15
 * @Version: 1.0
 */
public interface ListableBeanFactory extends BeanFactory {

    /**
     * 安装类型返回 Bean 实例
     * @param type
     * @param <T>
     * @return
     * @throws BeansException
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

    /**
     * 返回注册表中所有的 Bean 名称
     * @return
     */
    String[] getBeanDefinitionNames();

}
