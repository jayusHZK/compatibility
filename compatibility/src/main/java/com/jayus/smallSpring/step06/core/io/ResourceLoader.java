package com.jayus.smallSpring.step06.core.io;

/**
 * @author : h zk
 * @date : 2023/3/14 16:43
 * @description :
 **/
public interface ResourceLoader {

    String CLASSPATH_URL_PREFIX = "classpath:";

    Resource getResource(String location);

}
