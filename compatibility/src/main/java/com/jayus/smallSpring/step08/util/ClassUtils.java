package com.jayus.smallSpring.step08.util;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/3/21 22:50
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