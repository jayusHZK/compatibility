package com.jayus.smallSpring.step12.beans.factory.support;

import com.jayus.smallSpring.step12.beans.BeansException;
import com.jayus.smallSpring.step12.beans.factory.ConfigurableListableBeanFactory;
import com.jayus.smallSpring.step12.beans.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : h zk
 * @date : 2023/4/12 10:25
 * @description :
 **/
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry, ConfigurableListableBeanFactory {

    private Map<String, BeanDefinition> beandDefinitionMap = new ConcurrentHashMap<>();

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beandDefinitionMap.put(beanName, beanDefinition);
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return beandDefinitionMap.containsKey(beanName);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        Map<String, T> result = new HashMap<>();
        beandDefinitionMap.forEach((beanName, beandefinition) -> {
            Class beanClass = beandefinition.getBeanClass();
            if (type.isAssignableFrom(beanClass)){
                result.put(beanName, (T) getBean(beanName));
            }
        });
        return result;
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return beandDefinitionMap.keySet().toArray(new String[0]);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        BeanDefinition beanDefinition = beandDefinitionMap.get(beanName);
        if (beanDefinition == null) throw new BeansException("No bean named '" + beanName + "' is defined");
        return beanDefinition;
    }

    @Override
    public void preInstantiateSingletons() throws BeansException {
        beandDefinitionMap.keySet().forEach(this::getBean);
    }
}
