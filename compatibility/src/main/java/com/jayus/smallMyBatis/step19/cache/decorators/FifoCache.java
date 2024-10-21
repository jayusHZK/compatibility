package com.jayus.smallMyBatis.step19.cache.decorators;

import com.jayus.smallMyBatis.step19.cache.Cache;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @ClassName FifoCache
 * @Description: FIFO (first in, first out) cache decorator
 * @date: 2024/10/17 23:12
 */
public class FifoCache implements Cache {

    private final Cache delegate;

    private Deque<Object> keyList;

    private int size;

    public FifoCache(Cache delegate) {
        this.delegate = delegate;
        this.keyList = new LinkedList<>();
        this.size = 1024;
    }

    @Override
    public String getId() {
        return delegate.getId();
    }

    @Override
    public int getSize() {
        return delegate.getSize();
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public void putObject(Object key, Object value) {
        cycleKeyList(key);
        delegate.putObject(key,value);
    }

    @Override
    public Object getObject(Object key) {
        return delegate.getObject(key);
    }

    @Override
    public Object removeObject(Object key) {
        return delegate.removeObject(key);
    }

    @Override
    public void clear() {
        delegate.clear();
        keyList.clear();
    }

    /*
    满了后 删除最老的元素
     */
    private void cycleKeyList(Object key) {
        keyList.addLast(key);
        if (keyList.size() > size) {
            Object oldestKey = keyList.removeFirst();
            delegate.removeObject(oldestKey);
        }
    }

}
