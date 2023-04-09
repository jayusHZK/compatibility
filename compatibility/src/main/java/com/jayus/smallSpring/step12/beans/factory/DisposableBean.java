package com.jayus.smallSpring.step12.beans.factory;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/4/9 18:11
 * @Version: 1.0
 */
public interface DisposableBean {

    void destroy() throws Exception;

}
