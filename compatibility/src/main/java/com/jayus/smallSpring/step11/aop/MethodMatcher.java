package com.jayus.smallSpring.step11.aop;

import java.lang.reflect.Method;

/**
 * @author : h zk
 * @date : 2023/4/4 11:48
 * @description :
 **/
public interface MethodMatcher {

    boolean matches(Method method,Class<?> targetClass);

}
