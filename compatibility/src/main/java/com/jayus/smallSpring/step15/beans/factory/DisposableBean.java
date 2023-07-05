package com.jayus.smallSpring.step15.beans.factory;

/**
 * @author : h zk
 * @date : 2023/7/5 18:27
 * @description :
 **/
public interface DisposableBean {

    void destroy() throws Exception;

}
