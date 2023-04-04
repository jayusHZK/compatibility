package com.jayus.smallSpring.step11.aop;

/**
 * @author : h zk
 * @date : 2023/4/4 11:49
 * @description :
 **/
public interface Pointcut {

    ClassFilter getClassFilter();

    MethodMatcher getMethodMatcher();

}
