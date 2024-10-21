package com.jayus.smallMyBatis.step19.cache;

import com.jayus.smallMyBatis.step19.cache.decorators.TransactionalCache;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName TransactionalCacheManager
 * @Description: 事务缓存，管理器
 * @date: 2024/10/19 00:14
 */
public class TransactionalCacheManager {

    private Map<Cache, TransactionalCache> transactionalCaches = new HashMap<>();

    public void clear(Cache cache) {
        getTransactionalCache(cache).clear();
    }

    public Object getObject(Cache cache,CacheKey key) {
        return getTransactionalCache(cache).getObject(key);
    }

    public void putObject(Cache cache,CacheKey key,Object value) {
        getTransactionalCache(cache).putObject(key,value);
    }

    public void commit(){
        for (TransactionalCache txCache : transactionalCaches.values()) {
            txCache.commit();
        }
    }

    public void rollback(){
        for (TransactionalCache txCache : transactionalCaches.values()) {
            txCache.rollback();
        }
    }

    private TransactionalCache getTransactionalCache(Cache cache) {
        TransactionalCache txCache = transactionalCaches.get(cache);
        if (txCache == null) {
            txCache = new TransactionalCache(cache);
            transactionalCaches.put(cache,txCache);
        }
        return txCache;
    }

}
