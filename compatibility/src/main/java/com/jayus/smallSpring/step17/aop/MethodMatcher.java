package com.jayus.smallSpring.step17.aop;

import java.lang.reflect.Method;

/**
 * @author : h zk
 * @date : 2023/8/1 15:23
 * @description :
 **/
public interface MethodMatcher {

    boolean matches(Method method,Class<?> targetClass);

}
