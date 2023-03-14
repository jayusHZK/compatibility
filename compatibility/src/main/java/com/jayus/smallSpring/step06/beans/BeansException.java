package com.jayus.smallSpring.step06.beans;

/**
 * @author : h zk
 * @date : 2023/3/14 17:20
 * @description :
 **/
public class BeansException extends RuntimeException {

    public BeansException(String msg){
        super(msg);
    }

    public BeansException(String msg,Throwable cause){
        super(msg,cause);
    }

}
