package com.jayus.smallSpring.step16.core.io;

/**
 * @author : h zk
 * @date : 2023/7/14 10:22
 * @description :
 **/
public interface ResourceLoader {

    String CLASSPATH_URL_PREFIX = "classpath:";

    Resource getResource(String location);

}
