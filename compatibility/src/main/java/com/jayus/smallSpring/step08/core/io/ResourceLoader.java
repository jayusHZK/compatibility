package com.jayus.smallSpring.step08.core.io;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/3/21 23:01
 * @Version: 1.0
 */
public interface ResourceLoader {

    String CLASSPATH_URL_PREFIX = "classpath:";

    Resource getResource(String location);

}
