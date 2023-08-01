package com.jayus.smallSpring.step17.context.annotation;

import cn.hutool.core.util.ClassUtil;
import com.jayus.smallSpring.step17.beans.factory.config.BeanDefinition;
import com.jayus.smallSpring.step17.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author : h zk
 * @date : 2023/8/1 10:10
 * @description :
 **/
public class ClassPathScanningCandidateComponentProvider {

    public Set<BeanDefinition> findCandidateComponents(String basePackage){
        Set<BeanDefinition> candidates = new LinkedHashSet<>();
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(basePackage, Component.class);
        for (Class<?> clazz : classes) {
            candidates.add(new BeanDefinition(clazz));
        }
        return candidates;
    }

}
