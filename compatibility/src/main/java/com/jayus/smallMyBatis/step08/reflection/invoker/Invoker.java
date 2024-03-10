package com.jayus.smallMyBatis.step08.reflection.invoker;

/**
 * 反射调用器
 */
public interface Invoker {

    // 反射调用
    Object invoke(Object target,Object[] args) throws Exception;

    // 获取类型
    Class<?> getType();

}
