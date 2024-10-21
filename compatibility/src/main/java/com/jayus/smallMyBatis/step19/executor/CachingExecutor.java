package com.jayus.smallMyBatis.step19.executor;

import com.alibaba.fastjson.JSON;
import com.jayus.smallMyBatis.step19.cache.Cache;
import com.jayus.smallMyBatis.step19.cache.CacheKey;
import com.jayus.smallMyBatis.step19.cache.TransactionalCacheManager;
import com.jayus.smallMyBatis.step19.mapping.BoundSql;
import com.jayus.smallMyBatis.step19.mapping.MappedStatement;
import com.jayus.smallMyBatis.step19.session.ResultHandler;
import com.jayus.smallMyBatis.step19.session.RowBounds;
import com.jayus.smallMyBatis.step19.transaction.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName CacheingExecutor
 * @Description: 二级缓存执行器
 * @date: 2024/10/19 00:13
 */
public class CachingExecutor implements Executor{

    private Logger logger = LoggerFactory.getLogger(CachingExecutor.class);

    private Executor delegate;

    private TransactionalCacheManager tcm = new TransactionalCacheManager();

    public CachingExecutor(Executor delegate) {
        this.delegate = delegate;
        delegate.setExecutorWrapper(this);
    }

    @Override
    public int update(MappedStatement ms, Object parameter) throws SQLException {
        return delegate.update(ms,parameter);
    }

    @Override
    public <E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, CacheKey key, BoundSql boundSql) throws SQLException {
        Cache cache = ms.getCache();
        if (cache != null) {
            flushCacheIfRequired(ms);
            if (ms.isUseCache() && resultHandler == null) {
                List<E> list = (List<E>) tcm.getObject(cache,key);
                if (list == null) {
                    list = delegate.<E>query(ms,parameter,rowBounds,resultHandler,key,boundSql);
                    // cache：缓存队列实现类，FIFO
                    // key：哈希值 [mappedStatementId + offset + limit + SQL + queryParams + environment]
                    // list：查询的数据
                    tcm.putObject(cache,key,list);
                }
                if (logger.isDebugEnabled() && cache.getSize() > 0) {
                    logger.debug("二级缓存：{}", JSON.toJSONString(list));
                }
                return list;
            }
        }
        return delegate.<E>query(ms,parameter,rowBounds,resultHandler,key,boundSql);
    }

    @Override
    public <E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler) throws SQLException {
        BoundSql boundSql = ms.getBoundSql(parameter);
        CacheKey key = createCacheKey(ms, parameter, rowBounds, boundSql);
        return query(ms,parameter,rowBounds,resultHandler,key,boundSql);
    }

    @Override
    public Transaction getTransaction() {
        return delegate.getTransaction();
    }

    @Override
    public void commit(boolean required) throws SQLException {
        delegate.commit(required);
        tcm.commit();
    }

    @Override
    public void rollback(boolean required) throws SQLException {
        try {
            delegate.rollback(required);
        } finally {
            if (required) {
                tcm.rollback();
            }
        }
    }

    @Override
    public void close(boolean forceRollback) {
        try {
            if (forceRollback) {
                tcm.rollback();
            } else {
                tcm.commit();
            }
        } finally {
            delegate.close(forceRollback);
        }
    }

    @Override
    public void clearLocalCache() {
        delegate.clearLocalCache();
    }

    @Override
    public CacheKey createCacheKey(MappedStatement ms, Object parameterObject, RowBounds rowBounds, BoundSql boundSql) {
        return delegate.createCacheKey(ms,parameterObject,rowBounds,boundSql);
    }

    @Override
    public void setExecutorWrapper(Executor executor) {
        throw new UnsupportedOperationException("This method should not be called");
    }

    private void flushCacheIfRequired(MappedStatement ms) {
        Cache cache = ms.getCache();
        if (cache != null && ms.isFlushCacheRequired()) {
            tcm.clear(cache);
        }
    }

}
