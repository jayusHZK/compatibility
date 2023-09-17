package com.jayus.smallMyBatis.step06.executor;

import com.jayus.smallMyBatis.step06.mapping.BoundSql;
import com.jayus.smallMyBatis.step06.mapping.MappedStatement;
import com.jayus.smallMyBatis.step06.mapping.transaction.Transaction;
import com.jayus.smallMyBatis.step06.session.Configuration;
import com.jayus.smallMyBatis.step06.session.ResultHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * 执行器抽象基类 用于定义公共标准流程
 */
public abstract class BaseExecutor implements Executor {

    private org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(BaseExecutor.class);

    protected Configuration configuration;

    protected Transaction transaction;

    // 执行器对象
    protected Executor wrapper;

    // 是否关闭
    private boolean closed;

    public BaseExecutor(Configuration configuration, Transaction transaction) {
        this.configuration = configuration;
        this.transaction = transaction;
    }

    @Override
    public <E> List<E> query(MappedStatement ms, Object parameter, ResultHandler resultHandler, BoundSql boundSql) throws SQLException {
        if (closed){
            throw new RuntimeException("Executor was closed.");
        }
        return doQuery(ms,parameter,resultHandler,boundSql);
    }

    protected abstract <E> List<E> doQuery(MappedStatement ms, Object parameter, ResultHandler resultHandler, BoundSql boundSql);

    @Override
    public Transaction getTransaction() {
        if(closed){
            throw new RuntimeException("Executor was closed.");
        }
        return transaction;
    }

    @Override
    public void commit(boolean required) throws SQLException {
        if (closed) {
            throw new RuntimeException("Cannot commit, transaction is already closed");
        }
        if (required) {
            transaction.commit();
        }
    }

    /**
     * 回滚事务
     * @param required
     * @throws SQLException
     */
    @Override
    public void rollback(boolean required) throws SQLException {
        if (closed) {
            throw new RuntimeException("Cannot rollback, transaction is already closed");
        }
        if (required) {
            transaction.rollback();
        }
    }

    /**
     * 关闭连接
     * @param forceRollback
     * @throws SQLException
     */
    @Override
    public void close(boolean forceRollback) throws SQLException {
        try {
            try{
                rollback(forceRollback);
            } finally {
                transaction.close();
            }
        } catch (SQLException e){
            logger.warn("Unexpected exception on closing transaction.  Cause: " + e);
        } finally {
            transaction = null;
            closed = true;
        }
    }
}
