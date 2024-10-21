package com.jayus.smallMyBatis.step19.cache;

/**
 * @ClassName Cache
 * @Description: SPI(Service Provider Interface) for cache providers。缓存接口
 * @date: 2024/10/17 22:56
 */
public interface Cache {

    String getId();

    void putObject(Object key,Object value);

    Object getObject(Object key);

    Object removeObject(Object key);

    void clear();

    int getSize();

}
