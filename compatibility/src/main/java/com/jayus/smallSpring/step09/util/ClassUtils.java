package com.jayus.smallSpring.step09.util;

/**
 * @author : h zk
 * @date : 2023/3/28 9:55
 * @description :
 **/
public class ClassUtils {

    public static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        } catch (Exception e) {
            //e.printStackTrace();
        }
        if (cl == null){
            cl = ClassUtils.class.getClassLoader();
        }
        return cl;
    }

}
