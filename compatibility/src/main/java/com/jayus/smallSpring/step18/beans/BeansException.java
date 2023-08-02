package com.jayus.smallSpring.step18.beans;

/**
 * @author : h zk
 * @date : 2023/8/2 17:12
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
