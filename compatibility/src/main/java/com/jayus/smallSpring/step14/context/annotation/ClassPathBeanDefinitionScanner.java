package com.jayus.smallSpring.step14.context.annotation;

import cn.hutool.core.util.StrUtil;
import com.jayus.smallSpring.step14.beans.factory.config.BeanDefinition;
import com.jayus.smallSpring.step14.beans.factory.support.BeanDefinitionRegistry;
import com.jayus.smallSpring.step14.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.Set;

/**
 * @author : h zk
 * @date : 2023/6/26 17:05
 * @description :
 **/
public class ClassPathBeanDefinitionScanner extends ClassPathScanningCandidateCompentProvider {

    private BeanDefinitionRegistry registry;

    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    public void doScan(String... basePackages){
        for (String basePackage : basePackages) {
            Set<BeanDefinition> candidates = findCandidateComponents(basePackage);
            for (BeanDefinition beanDefinition : candidates) {
                String beanScope = resolveBeanScope(beanDefinition);
                if (StrUtil.isNotEmpty(beanScope)){
                    beanDefinition.setScope(beanScope);
                }
                registry.registerBeanDefinition(determineBeanName(beanDefinition),beanDefinition);
            }
        }
    }

    private String resolveBeanScope(BeanDefinition beanDefinition){
        Class<?> beanClass = beanDefinition.getBeanClass();
        Scope scope = beanClass.getAnnotation(Scope.class);
        if (null != scope) return scope.value();
        return StrUtil.EMPTY;
    }

    private String determineBeanName(BeanDefinition beanDefinition){
        Class<?> beanClass = beanDefinition.getBeanClass();
        Component component = beanClass.getAnnotation(Component.class);
        String value = component.value();
        if (StrUtil.isNotEmpty(value)){
            value = StrUtil.lowerFirst(beanClass.getSimpleName());
        }
        return value;
    }

}
