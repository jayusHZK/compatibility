package com.jayus.smallSpring.step14.aop;

/**
 * @author : h zk
 * @date : 2023/6/21 9:59
 * @description :
 **/
public interface PointcutAdvisor extends Advisor{

    Pointcut getPointcut();

}
