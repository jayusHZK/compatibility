package com.jayus.smallSpring.step14.beans.factory;

/**
 * @author : h zk
 * @date : 2023/6/21 13:52
 * @description :
 **/
public interface DisposableBean {

    void destroy() throws Exception;

}
