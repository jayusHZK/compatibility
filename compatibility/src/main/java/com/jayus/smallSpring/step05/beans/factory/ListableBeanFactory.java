package com.jayus.smallSpring.step05.beans.factory;

import com.jayus.smallSpring.step05.beans.BeansException;

import java.util.Map;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/3/12 20:25
 * @Version: 1.0
 */
public interface ListableBeanFactory extends BeanFactory {

    /**
     * 按照类型返回Bean实例
     * @param type
     * @param <T>
     * @return
     * @throws BeansException
     */
    <T> Map<String,T> getBeansOfType(Class<T> type) throws BeansException;

    /**
     * 返回注册表中所有的Bean名称
     * @return
     */
    String[] getBeanDefinitionNames();

}
