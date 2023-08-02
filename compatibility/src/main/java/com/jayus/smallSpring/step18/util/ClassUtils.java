package com.jayus.smallSpring.step18.util;

/**
 * @author : h zk
 * @date : 2023/8/2 14:29
 * @description :
 **/
public class ClassUtils {

    public static ClassLoader getDefaultClassLoader(){
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cl == null){
            cl = ClassUtils.class.getClassLoader();
        }
        return cl;
    }

    public static boolean isCglibProxyClass(Class<?> clazz){
        return clazz != null && isCglibProxyClassName(clazz.getName());
    }

    public static boolean isCglibProxyClassName(String className){
        return className != null && className.contains("$$");
    }

}
