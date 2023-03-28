package com.jayus.smallSpring.step09.core.io;

/**
 * @author : h zk
 * @date : 2023/3/28 9:58
 * @description :
 **/
public interface ResourceLoader {

    String CLSSSPATH_URL_PREFIX = "classpath:";

    Resource getResource(String location);

}
