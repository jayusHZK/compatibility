package com.jayus.smallSpring.step09.beans.factory;

/**
 * @author : h zk
 * @date : 2023/3/28 11:03
 * @description :
 **/
public interface InitializingBean {

    /**
     *  Bean 处理了属性填充后调用
     * @throws Exception
     */
    void afterPropertiesSer() throws Exception;

}
