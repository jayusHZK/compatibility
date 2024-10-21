package com.jayus.smallMyBatis.step19.executor;

import com.jayus.smallMyBatis.step19.cache.CacheKey;
import com.jayus.smallMyBatis.step19.mapping.BoundSql;
import com.jayus.smallMyBatis.step19.mapping.MappedStatement;
import com.jayus.smallMyBatis.step19.session.ResultHandler;
import com.jayus.smallMyBatis.step19.session.RowBounds;
import com.jayus.smallMyBatis.step19.transaction.Transaction;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName Executor
 * @Description: 执行器
 * @date: 2024/10/18 12:25
 */
public interface Executor {

    ResultHandler NO_RESULT_HANDLER = null;

    int update(MappedStatement ms,Object parameter) throws SQLException;

    // 查询，含缓存
    <E>List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, CacheKey key, BoundSql boundSql) throws SQLException;

    <E>List<E> query(MappedStatement ms,Object parameter,RowBounds rowBounds,ResultHandler resultHandler) throws SQLException;

    Transaction getTransaction();

    void commit(boolean required) throws SQLException;

    void rollback(boolean required) throws SQLException;

    void close(boolean forceRollback);
    // 清理Session缓存
    void clearLocalCache();

    // 创建缓存 Key
    CacheKey createCacheKey(MappedStatement ms,Object parameterObject,RowBounds rowBounds,BoundSql boundSql);

    void setExecutorWrapper(Executor executor);

}
