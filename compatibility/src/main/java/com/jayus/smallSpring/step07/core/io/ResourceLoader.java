package com.jayus.smallSpring.step07.core.io;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/3/16 23:13
 * @Version: 1.0
 */
public interface ResourceLoader {

    String CLASSPATH_URL_PREFIX = "classpath:";

    Resource getResource(String location);

}
