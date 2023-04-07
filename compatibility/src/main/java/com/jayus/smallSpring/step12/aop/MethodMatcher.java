package com.jayus.smallSpring.step12.aop;

import java.lang.reflect.Method;

/**
 * @author : h zk
 * @date : 2023/4/7 15:00
 * @description :
 **/
public interface MethodMatcher {

    boolean matches(Method method,Class<?> targetClass);

}
