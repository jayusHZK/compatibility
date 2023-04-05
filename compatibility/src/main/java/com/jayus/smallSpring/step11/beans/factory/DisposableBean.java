package com.jayus.smallSpring.step11.beans.factory;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/4/5 22:06
 * @Version: 1.0
 */
public interface DisposableBean {

    void destroy() throws Exception;

}
