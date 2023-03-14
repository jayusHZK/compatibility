package com.jayus.smallSpring.step05.beans;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/3/12 13:35
 * @Version: 1.0
 */
public class BeansException extends RuntimeException{

    public BeansException(String msg) {
        super(msg);
    }

    public BeansException(String msg, Throwable cause) {
        super(msg,cause);
    }

}