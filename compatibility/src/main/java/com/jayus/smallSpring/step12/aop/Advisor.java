package com.jayus.smallSpring.step12.aop;

import org.aopalliance.aop.Advice;

/**
 * @author : h zk
 * @date : 2023/4/7 15:01
 * @description :
 **/
public interface Advisor {

    Advice getAdvice();

}
