package com.jayus.smallSpring.step07.util;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/3/16 23:10
 * @Version: 1.0
 */
public class ClassUtils {

    public static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        } catch (Exception e) {

        }
        if (cl == null){
            cl = ClassUtils.class.getClassLoader();
        }
        return cl;
    }

}