package com.jayus.smallMyBatis.step11.executor;

import com.jayus.smallMyBatis.step11.mapping.BoundSql;
import com.jayus.smallMyBatis.step11.mapping.MappedStatement;
import com.jayus.smallMyBatis.step11.session.ResultHandler;
import com.jayus.smallMyBatis.step11.session.RowBounds;
import com.jayus.smallMyBatis.step11.transaction.Transaction;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName Executor
 * @Description: 执行器
 * @date: 2024/5/13 18:05
 */
public interface Executor {

    ResultHandler NO_RESULT_HANDLER = null;

    <E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql);

    Transaction getTransaction();

    void commit(boolean required) throws SQLException;

    void rollback(boolean required) throws SQLException;

    void close(boolean forceRollback);

}
