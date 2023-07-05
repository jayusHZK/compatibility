package com.jayus.smallSpring.step15.aop;

import java.lang.reflect.Method;

/**
 * @author : h zk
 * @date : 2023/7/5 9:54
 * @description :
 **/
public interface MethodMatcher {

    boolean matches(Method method,Class<?> targetClass);

}
