package com.jayus.smallSpring.step14.beans;

/**
 * @author : h zk
 * @date : 2023/6/21 11:48
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
