package com.jayus.smallSpring.step15.beans;

/**
 * @author : h zk
 * @date : 2023/7/5 18:16
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
