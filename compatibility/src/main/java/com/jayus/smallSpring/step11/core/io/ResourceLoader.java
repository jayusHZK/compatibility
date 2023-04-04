package com.jayus.smallSpring.step11.core.io;

/**
 * @author : h zk
 * @date : 2023/4/4 17:19
 * @description :
 **/
public interface ResourceLoader {

    String CLASSPATH_URL_PREFIX = "classpath:";

    Resource getResource(String location);

}
