package com.jayus.smallSpring.step11.beans;

/**
 * @author : h zk
 * @date : 2023/4/4 18:08
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
