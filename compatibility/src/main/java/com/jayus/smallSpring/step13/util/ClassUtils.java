package com.jayus.smallSpring.step13.util;

public class ClassUtils {

    public static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        } catch (Exception e) {
            //
        }
        if (cl == null) {
            cl = ClassUtils.class.getClassLoader();
        }
        return cl;
    }

    public static boolean isCglibProxyClass(Class<?> clazz) {
        return clazz != null && isCgligClassName(clazz.getName());
    }

    public static boolean isCgligClassName(String className) {
        return className != null && className.contains("$$");
    }

}
