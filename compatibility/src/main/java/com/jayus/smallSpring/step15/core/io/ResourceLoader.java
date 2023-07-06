package com.jayus.smallSpring.step15.core.io;

/**
 * @author : h zk
 * @date : 2023/7/6 14:17
 * @description :
 **/
public interface ResourceLoader {

    String classpath_url_orefix = "classpath:";

    Resource getResource(String location);

}
