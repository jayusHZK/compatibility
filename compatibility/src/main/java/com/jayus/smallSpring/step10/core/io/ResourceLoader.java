package com.jayus.smallSpring.step10.core.io;

/**
 * @author : h zk
 * @date : 2023/3/31 11:41
 * @description :
 **/
public interface ResourceLoader {

    String CLASSPATH_URL_PREFIX = "classpath:";

    Resource getResource(String location);

}
