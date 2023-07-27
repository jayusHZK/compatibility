package com.jayus.smallSpring.step17.beans;

/**
 * @author : h zk
 * @date : 2023/7/27 10:20
 * @description :
 **/
public class BeansException extends RuntimeException{

    public BeansException(String message) {
        super(message);
    }

    public BeansException(String message, Throwable cause) {
        super(message, cause);
    }
}
