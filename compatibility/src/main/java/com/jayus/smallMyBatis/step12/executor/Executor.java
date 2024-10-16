package com.jayus.smallMyBatis.step12.executor;

import com.jayus.smallMyBatis.step12.mapping.BoundSql;
import com.jayus.smallMyBatis.step12.mapping.MappedStatement;
import com.jayus.smallMyBatis.step12.session.ResultHandler;
import com.jayus.smallMyBatis.step12.session.RowBounds;
import com.jayus.smallMyBatis.step12.transaction.Transaction;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName Executor
 * @Description: 执行器
 * @date: 2024/9/25 10:11
 */
public interface Executor {

    ResultHandler NO_RESULT_HANDLER = null;

    int update(MappedStatement ms,Object parameter) throws SQLException;

    <E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) throws SQLException;

    Transaction getTransaction();

    void commit(boolean required) throws SQLException;

    void rollback(boolean required) throws SQLException;

    void close(boolean forceRollback);

}
