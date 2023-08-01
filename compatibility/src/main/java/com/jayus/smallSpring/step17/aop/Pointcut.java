package com.jayus.smallSpring.step17.aop;

/**
 * @author : h zk
 * @date : 2023/8/1 15:24
 * @description :
 **/
public interface Pointcut {

    ClassFilter getClassFilter();

    MethodMatcher getMethodMatcher();

}
