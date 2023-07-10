package com.jayus.smallSpring.step15.context.annotation;

import cn.hutool.core.util.ClassUtil;
import com.jayus.smallSpring.step15.beans.factory.config.BeanDefinition;
import com.jayus.smallSpring.step15.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author : h zk
 * @date : 2023/7/10 16:40
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
