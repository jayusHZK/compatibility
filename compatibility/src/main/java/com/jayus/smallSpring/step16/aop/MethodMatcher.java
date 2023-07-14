package com.jayus.smallSpring.step16.aop;

import java.lang.reflect.Method;

/**
 * @author : h zk
 * @date : 2023/7/14 10:01
 * @description :
 **/
public interface MethodMatcher {

    boolean matches(Method method,Class<?> targetClass);

}
