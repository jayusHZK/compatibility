package com.jayus.smallSpring.step09.beans;

/**
 * @author : h zk
 * @date : 2023/3/28 10:21
 * @description :
 **/
public class BeansException extends RuntimeException {

    public BeansException(String message) {
        super(message);
    }

    public BeansException(String message, Throwable cause) {
        super(message, cause);
    }
}
