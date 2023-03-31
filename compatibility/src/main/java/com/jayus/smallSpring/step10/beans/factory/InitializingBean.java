package com.jayus.smallSpring.step10.beans.factory;

/**
 * @author : h zk
 * @date : 2023/3/31 15:17
 * @description :
 **/
public interface InitializingBean {

    /**
     * Bean 处理了属性填充后调用
     * @throws Exception
     */
    void afterPropertiesSet() throws Exception;

}
