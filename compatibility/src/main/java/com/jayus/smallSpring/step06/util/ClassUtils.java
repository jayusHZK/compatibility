package com.jayus.smallSpring.step06.util;

import com.jayus.vo.UserVO;

import java.util.function.Function;

/**
 * @author : h zk
 * @date : 2023/3/14 11:10
 * @description :
 **/
public class ClassUtils {

    Function<UserVO,String> fun = UserVO::getUsername;

    public static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        } catch (Exception e) {

        }
        if (cl == null) {
            cl = ClassUtils.class.getClassLoader();
        }
        return cl;
    }

}
