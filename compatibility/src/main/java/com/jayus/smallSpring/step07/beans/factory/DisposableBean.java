package com.jayus.smallSpring.step07.beans.factory;

/**
 * @author : h zk
 * @date : 2023/3/17 14:33
 * @description :
 **/
public interface DisposableBean {

    void destroy() throws Exception;

}
