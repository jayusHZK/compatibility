package com.jayus.smallSpring.step15.core.io;

/**
 * @author : h zk
 * @date : 2023/7/6 14:17
 * @description :
 **/
public interface ResourceLoader {

    String CLASSPATH_URL_PREFIX = "classpath:";

    Resource getResource(String location);

}
