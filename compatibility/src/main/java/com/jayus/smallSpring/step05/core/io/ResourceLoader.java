package com.jayus.smallSpring.step05.core.io;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/3/12 21:45
 * @Version: 1.0
 */
public interface ResourceLoader {

    String CLASSPATH_URL_PREFIX = "classpath:";

    Resource getResource(String location);

}