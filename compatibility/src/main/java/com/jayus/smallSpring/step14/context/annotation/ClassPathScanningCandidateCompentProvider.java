package com.jayus.smallSpring.step14.context.annotation;

import cn.hutool.core.util.ClassUtil;
import com.jayus.smallSpring.step14.beans.factory.config.BeanDefinition;
import com.jayus.smallSpring.step14.stereotype.Component;
import com.jayus.smallSpring.step14.util.ClassUtils;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author : h zk
 * @date : 2023/6/26 17:06
 * @description :
 **/
public class ClassPathScanningCandidateCompentProvider {

    public Set<BeanDefinition> findCandidateComponents(String basePackage){
        Set<BeanDefinition> candidates = new LinkedHashSet<>();
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(basePackage, Component.class);
        for (Class<?> clazz : classes) {
            candidates.add(new BeanDefinition(clazz));
        }
        return candidates;
    }

}
