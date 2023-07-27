package com.jayus.smallSpring.step17.beans.factory;

/**
 * @author : h zk
 * @date : 2023/7/27 11:20
 * @description :
 **/
public interface InitializingBean {

    void afterPropertiesSet()  throws Exception;

}
