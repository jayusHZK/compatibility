package com.jayus.smallSpring.step14.beans.factory;

/**
 * @author : h zk
 * @date : 2023/6/21 14:13
 * @description :
 **/
public interface InitializingBean {

    void afterPropertiesSet() throws Exception;

}
