package com.jayus.smallSpring.step08.beans;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/3/21 22:52
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