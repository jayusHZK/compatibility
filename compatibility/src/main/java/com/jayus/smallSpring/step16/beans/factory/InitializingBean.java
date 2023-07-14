package com.jayus.smallSpring.step16.beans.factory;

/**
 * @author : h zk
 * @date : 2023/7/14 11:27
 * @description :
 **/
public interface InitializingBean {

    void afterPropertiesSet() throws Exception;

}
