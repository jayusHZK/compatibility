package com.jayus.smallSpring.step18.context.annotation;

import cn.hutool.core.util.ClassUtil;
import com.jayus.smallSpring.step18.beans.factory.config.BeanDefinition;
import com.jayus.smallSpring.step18.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

public class ClassPathScanningCandidateComponentProvider {

    public Set<BeanDefinition> findCandidateComponentProvider(String basePackage){
        Set<BeanDefinition> candidates = new LinkedHashSet<>();
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(basePackage, Component.class);
        for (Class<?> clazz : classes) {
            candidates.add(new BeanDefinition(clazz));
        }
        return candidates;
    }

}
