package com.jayus.smallSpring.step15.aop;

/**
 * @author : h zk
 * @date : 2023/7/5 9:49
 * @description :
 **/
public interface ClassFilter {

    boolean matches(Class<?> clazz);

}
