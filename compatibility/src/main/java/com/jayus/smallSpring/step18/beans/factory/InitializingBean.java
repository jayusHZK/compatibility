package com.jayus.smallSpring.step18.beans.factory;

/**
 * @author : h zk
 * @date : 2023/8/2 17:30
 * @description :
 **/
public interface InitializingBean {

    void afterPropertiesSet() throws Exception;

}
