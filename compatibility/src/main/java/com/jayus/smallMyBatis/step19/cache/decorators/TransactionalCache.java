package com.jayus.smallMyBatis.step19.cache.decorators;

import com.jayus.smallMyBatis.step19.cache.Cache;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName TransactionalCache
 * @Description: The 2nd level cache transactional buffer. 事务缓存
 * @date: 2024/10/17 23:18
 */
public class TransactionalCache implements Cache {

    private Cache delegate;

    private boolean clearOnCommit;

    private Map<Object, Object> entriesToAddOnCommit;

    private Set<Object> entriesMissedInCache;

    public TransactionalCache(Cache delegate) {
        this.delegate = delegate;
        this.clearOnCommit = false;
        this.entriesToAddOnCommit = new HashMap<>();
        this.entriesMissedInCache = new HashSet<>();
    }

    @Override
    public String getId() {
        return delegate.getId();
    }

    @Override
    public void putObject(Object key, Object object) {
        entriesToAddOnCommit.put(key,object);
    }

    @Override
    public Object getObject(Object key) {
        Object object = delegate.getObject(key);
        if (object == null) {
            entriesMissedInCache.add(key);
        }
        return clearOnCommit ? null : object;
    }

    @Override
    public Object removeObject(Object key) {
        return null;
    }

    @Override
    public void clear() {
        clearOnCommit = true;
        entriesToAddOnCommit.clear();
    }

    @Override
    public int getSize() {
        return delegate.getSize();
    }

    public void commit(){
        if (clearOnCommit) {
            delegate.clear();
        }
        flushPendingEntries();
        reset();
    }

    public void rollback(){
        unlockMissedEntries();
        reset();
    }

    private void reset(){
        clearOnCommit = false;
        entriesToAddOnCommit.clear();
        entriesMissedInCache.clear();
    }

    /**
     * 刷新数据到 MappedStatement#Cache 中，也就是把数据填充到 Mapper XML 级别下。
     * flushPendingEntries 方法把事务缓存下的数据，填充到 FifoCache 中。
     */
    private void flushPendingEntries(){
        for (Map.Entry<Object, Object> entry : entriesToAddOnCommit.entrySet()) {
            delegate.putObject(entry.getKey(),entry.getValue());
        }
        for (Object entry : entriesMissedInCache) {
            if (!entriesToAddOnCommit.containsKey(entry)){
                delegate.putObject(entry,null);
            }
        }
    }

    private void unlockMissedEntries(){
        for (Object entry : entriesMissedInCache) {
            delegate.putObject(entry,null);
        }
    }
}
