package com.jayus.smallMyBatis.step03.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class Resources {

    public static Reader getResourceAsReader(String resource) throws IOException {
        return new InputStreamReader(getResourceAsStream(resource));
    }

    /**
     * 根据资源路径获取资源流
     * @param resource
     * @return
     * @throws IOException
     */
    private static InputStream getResourceAsStream(String resource) throws IOException {
        ClassLoader[] classLoaders = getClassLoaders();
        for (ClassLoader classLoader : classLoaders) {
            InputStream inputStream = classLoader.getResourceAsStream(resource);
            if (null != inputStream){
                return inputStream;
            }
        }
        throw new IOException("Could not find resource " + resource);
    }

    /**
     * 返回默认的类加载器
     * @return
     */
    private static ClassLoader[] getClassLoaders(){
        return new ClassLoader[]{
                ClassLoader.getSystemClassLoader(),
                Thread.currentThread().getContextClassLoader()};
    }

    /**
     * 通过包路径结构+类名获取类
     * @param className
     * @return
     * @throws ClassNotFoundException
     */
    public static Class<?> classForName(String className) throws ClassNotFoundException {
        return Class.forName(className);
    }
}
