package com.jayus.smallSpring.step07.beans;

/**
 * @author : h zk
 * @date : 2023/3/17 13:52
 * @description :
 **/
public class BeansException extends RuntimeException {

    public BeansException(String msg) {
        super(msg);
    }

    public BeansException(String message, Throwable cause) {
        super(message, cause);
    }
}
