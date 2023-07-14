package com.jayus.smallSpring.step16.aop;

/**
 * @author : h zk
 * @date : 2023/7/14 10:00
 * @description :
 **/
public interface ClassFilter {

    boolean matches(Class<?> clazz);

}
