package com.jayus.smallSpring.step05.util;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/3/12 22:17
 * @Version: 1.0
 */
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

}