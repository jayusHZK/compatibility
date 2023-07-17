package com.jayus.smallSpring.step16.aop;

import com.jayus.smallSpring.step16.util.ClassUtils;

/**
 * @author : h zk
 * @date : 2023/7/17 17:22
 * @description :
 **/
public class TargetSource {

    private final Object target;

    public TargetSource(Object target) {
        this.target = target;
    }

    public Class<?>[] getTargetClass(){
        Class<?> clazz = this.target.getClass();
        clazz = ClassUtils.isCglibProxyClass(clazz) ? clazz.getSuperclass() : clazz;
        return clazz.getInterfaces();
    }

    public Object getTarget() {
        return target;
    }
}
