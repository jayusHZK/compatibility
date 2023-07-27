package com.jayus.smallSpring.step17.core.io;

/**
 * @author : h zk
 * @date : 2023/7/27 11:49
 * @description :
 **/
public interface ResourceLoader {

    String CLASSPATH_URL_PREFIX = "classpath:";

    Resource getResource(String location);

}
