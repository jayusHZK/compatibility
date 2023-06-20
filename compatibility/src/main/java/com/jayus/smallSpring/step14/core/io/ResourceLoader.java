package com.jayus.smallSpring.step14.core.io;

/**
 * @author : h zk
 * @date : 2023/6/20 17:34
 * @description :
 **/
public interface ResourceLoader {

    String CLASSPATH_URL_PREFIX = "classpath:";

    Resource getResource(String location);

}
