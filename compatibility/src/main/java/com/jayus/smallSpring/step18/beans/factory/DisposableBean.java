package com.jayus.smallSpring.step18.beans.factory;

/**
 * @author : h zk
 * @date : 2023/8/2 17:28
 * @description :
 **/
public interface DisposableBean {

    void destroy() throws Exception;

}
