package com.jayus.smallSpring.step17.beans.factory;

/**
 * @author : h zk
 * @date : 2023/7/27 11:18
 * @description :
 **/
public interface DisposableBean {

    void destroy() throws Exception;

}
