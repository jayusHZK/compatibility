package com.jayus.smallSpring.step16.context.annotation;

import cn.hutool.core.util.StrUtil;
import com.jayus.smallSpring.step16.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import com.jayus.smallSpring.step16.beans.factory.config.BeanDefinition;
import com.jayus.smallSpring.step16.beans.factory.support.BeanDefinitionRegistry;
import com.jayus.smallSpring.step16.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Set;

/**
 * @author : h zk
 * @date : 2023/7/17 14:31
 * @description :
 **/
public class ClassPathBeanDefinitionScanner extends ClassPathScanningCandidateComponentProvider{

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
                registry.registerBeanDefinition(determineBeanScope(beanDefinition),beanDefinition);
            }
        }
        registry.registerBeanDefinition("",new BeanDefinition(AutowiredAnnotationBeanPostProcessor.class));
    }

    private String resolveBeanScope(BeanDefinition beanDefinition){
        Class<?> beanClass = beanDefinition.getBeanClass();
        Scope scope = beanClass.getAnnotation(Scope.class);
        if (null != scope) return scope.value();
        return StrUtil.EMPTY;
    }

    private String determineBeanScope(BeanDefinition beanDefinition){
        Class<?> beanClass = beanDefinition.getBeanClass();
        Component component = beanClass.getAnnotation(Component.class);
        String value = component.value();
        if (StrUtil.isEmpty(value)){
            value = StrUtil.lowerFirst(beanClass.getSimpleName());
        }
        return value;
    }

}
