package com.jayus.smallSpring.step18.aop;

public interface PointcutAdvisor extends Advisor{

    Pointcut getPointcut();

}
