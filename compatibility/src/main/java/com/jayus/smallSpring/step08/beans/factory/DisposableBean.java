package com.jayus.smallSpring.step08.beans.factory;

/**
 * @author : h zk
 * @date : 2023/3/23 19:25
 * @description :
 **/
public interface DisposableBean {

    void destroy() throws Exception;

}
