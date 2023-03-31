package com.jayus.smallSpring.step10.core.io;

import com.jayus.smallSpring.step10.util.ClassUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author : h zk
 * @date : 2023/3/31 11:48
 * @description :
 **/
public class ClassPathResource implements Resource{

    private final String path;

    private ClassLoader classLoader;

    public ClassPathResource(String path) {
        this(path,null);
    }

    public ClassPathResource(String path, ClassLoader classLoader) {
        this.path = path;
        this.classLoader = classLoader != null ? classLoader: ClassUtils.getDefaultClassLoader();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        InputStream is = classLoader.getResourceAsStream(path);
        if (is == null){
            throw new FileNotFoundException(
                    this.path + " cannot be opened because it does not exist");
        }
        return is;
    }
}
