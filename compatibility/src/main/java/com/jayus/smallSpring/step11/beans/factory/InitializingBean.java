package com.jayus.smallSpring.step11.beans.factory;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/4/5 22:02
 * @Version: 1.0
 */
public interface InitializingBean {

    /**
     * Bean 处理了属性填充后调用
     * @throws Exception
     */
    void afterPropertySer() throws Exception;

}