package com.jayus.smallSpring.step14.aop;

import java.lang.reflect.Method;

/**
 * @author : h zk
 * @date : 2023/6/21 9:54
 * @description :
 **/
public interface MethodMatcher {

    boolean matches(Method method,Class<?> targetClass);

}
