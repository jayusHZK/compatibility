package com.jayus.smallSpring.step15.aop;

import org.aopalliance.aop.Advice;

/**
 * @author : h zk
 * @date : 2023/7/5 9:44
 * @description :
 **/
public interface Advisor {

    Advice getAdvice();

}
