package com.jayus.smallMyBatis.step19.cache.Impl;

import com.jayus.smallMyBatis.step19.cache.Cache;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName PerpetualCache
 * @Description: 一级缓存，在 Session 生命周期内一直保持，每创建新的 OpenSession 都会创建一个缓存器 PerpetualCache
 * @date: 2024/10/17 23:25
 */
public class PerpetualCache implements Cache {

    private String id;

    private Map<Object,Object> cache = new HashMap<>();

    public PerpetualCache(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void putObject(Object key, Object value) {
        cache.put(key,value);
    }

    @Override
    public Object getObject(Object key) {
        return cache.get(key);
    }

    @Override
    public Object removeObject(Object key) {
        return cache.remove(key);
    }

    @Override
    public void clear() {
        cache.clear();
    }

    @Override
    public int getSize() {
        return cache.size();
    }
}
