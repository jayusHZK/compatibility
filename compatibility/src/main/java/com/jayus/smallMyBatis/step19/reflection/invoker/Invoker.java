package com.jayus.smallMyBatis.step19.reflection.invoker;

/**
 * @ClassName Invoker
 * @Description: 调用者
 * @date: 2024/10/16 21:32
 */
public interface Invoker {

    Object invoke(Object target,Object[] args) throws Exception;

    Class<?> getType();

}
