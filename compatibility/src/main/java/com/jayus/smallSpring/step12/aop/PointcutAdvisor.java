package com.jayus.smallSpring.step12.aop;

/**
 * @author : h zk
 * @date : 2023/4/7 15:04
 * @description :
 **/
public interface PointcutAdvisor extends Advisor{

    Pointcut getPointcut();

}
