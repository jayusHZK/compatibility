package com.jayus.smallSpring.step12.aop;

/**
 * @author : h zk
 * @date : 2023/4/7 14:59
 * @description :
 **/
public interface Pointcut {

    ClassFilter getClassFilter();

    MethodMatcher getMethodMatcher();

}
