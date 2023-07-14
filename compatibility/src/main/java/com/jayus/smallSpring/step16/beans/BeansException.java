package com.jayus.smallSpring.step16.beans;

/**
 * @author : h zk
 * @date : 2023/7/14 10:06
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
