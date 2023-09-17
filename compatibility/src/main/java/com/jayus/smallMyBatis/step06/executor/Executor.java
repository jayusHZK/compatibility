package com.jayus.smallMyBatis.step06.executor;

import com.jayus.smallMyBatis.step06.mapping.BoundSql;
import com.jayus.smallMyBatis.step06.mapping.MappedStatement;
import com.jayus.smallMyBatis.step06.mapping.transaction.Transaction;
import com.jayus.smallMyBatis.step06.session.ResultHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * 执行器接口
 */
public interface Executor {

    ResultHandler NO_RESULT_HANDLER = null;

    /**
     * 查询操作
     * @param ms
     * @param parameter
     * @param resultHandler
     * @param boundSql
     * @return
     * @param <E>
     * @throws SQLException
     */
    <E>List<E> query(MappedStatement ms, Object parameter, ResultHandler resultHandler, BoundSql boundSql) throws SQLException;

    /**
     * 获取事务
     * @return
     */
    Transaction getTransaction();

    void commit(boolean required) throws SQLException;

    void rollback(boolean required) throws SQLException;

    void close(boolean forceRollback) throws SQLException;

}
