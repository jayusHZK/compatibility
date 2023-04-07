package com.jayus.smallSpring.step12.core.io;

/**
 * @author : h zk
 * @date : 2023/4/7 14:33
 * @description :
 **/
public interface ResourceLoader {

    String CLASSPATH_URL_PREFIX = "classpath:";

    Resource getResource(String location);

}
