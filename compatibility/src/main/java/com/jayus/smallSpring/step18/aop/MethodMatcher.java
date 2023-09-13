package com.jayus.smallSpring.step18.aop;

import java.lang.reflect.Method;

public interface MethodMatcher {

    boolean matches(Method method,Class<?> targetCLass);

}
