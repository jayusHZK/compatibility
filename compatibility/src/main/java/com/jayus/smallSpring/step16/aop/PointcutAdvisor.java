package com.jayus.smallSpring.step16.aop;

/**
 * @author : h zk
 * @date : 2023/7/17 17:22
 * @description :
 **/
public interface PointcutAdvisor extends Advisor{

    Pointcut getPointcut();

}
