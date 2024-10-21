package com.jayus.smallMyBatis.step19.plugin;

/*
方法签名
 */
public @interface Signature{

    /*
    被拦截类
     */
    Class<?> type();

    /*
    被拦截类的方法
     */
    String method();

    /*
    被拦截类的方法的参数，只是帮助确认方法
     */
    Class<?>[] args();

}
