package com.jayus.smallSpring.step18.core.io;

/**
 * @author : h zk
 * @date : 2023/8/2 14:41
 * @description :
 **/
public interface ResourceLoader {

    String CLASSPATH_URL_PREFIX = "classpath:";

    Resource getResource(String location);

}
