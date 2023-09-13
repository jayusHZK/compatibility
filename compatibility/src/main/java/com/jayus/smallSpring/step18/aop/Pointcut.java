package com.jayus.smallSpring.step18.aop;

public interface Pointcut {

    ClassFilter getClassFilter();

    MethodMatcher getMethodMatcher();

}
