package com.jayus.smallSpring.step12.beans;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/4/9 18:02
 * @Version: 1.0
 */
public class BeansException extends RuntimeException {

    public BeansException(String message) {
        super(message);
    }

    public BeansException(String message, Throwable cause) {
        super(message, cause);
    }
}