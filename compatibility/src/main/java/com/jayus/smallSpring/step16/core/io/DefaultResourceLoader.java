package com.jayus.smallSpring.step16.core.io;

import cn.hutool.core.lang.Assert;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author : h zk
 * @date : 2023/7/14 10:28
 * @description :
 **/
public class DefaultResourceLoader implements ResourceLoader {

    @Override
    public Resource getResource(String location) {
        Assert.notNull(location, "Location must not be null");
        if (location.startsWith(CLASSPATH_URL_PREFIX)){
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
        } else {
            try {
                URL url = new URL(location);
                return new UrlResource(url);
            } catch (MalformedURLException e) {
                return new FileSystemResource(location);
            }
        }
    }
}
