package com.jayus.smallMyBatis.step10.executor;

import com.jayus.smallMyBatis.step10.mapping.BoundSql;
import com.jayus.smallMyBatis.step10.mapping.MappedStatement;
import com.jayus.smallMyBatis.step10.session.Configuration;
import com.jayus.smallMyBatis.step10.session.ResultHandler;
import com.jayus.smallMyBatis.step10.transaction.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

/**
 * 执行器抽象基类
 */
public abstract class BaseExecutor implements Executor {

    private Logger logger = LoggerFactory.getLogger(BaseExecutor.class);

    protected Configuration configuration;

    protected Transaction transaction;

    protected Executor wrapper;

    private boolean closed;

    public BaseExecutor(Configuration configuration, Transaction transaction) {
        this.configuration = configuration;
        this.transaction = transaction;
        this.wrapper = this;
    }

    @Override
    public <E> List<E> query(MappedStatement ms, Object parameter, ResultHandler resultHandler, BoundSql boundSql) {
        if (closed) {
            throw new RuntimeException("Executor was closed.");
        }
        return doQuery(ms,parameter,resultHandler,boundSql);
    }

    protected abstract  <E> List<E> doQuery(MappedStatement ms,Object parameter,ResultHandler resultHandler,BoundSql boundSql);

    @Override
    public Transaction getTranSaction() {
        if (closed) {
            throw new RuntimeException("Executor was closed.");
        }
        return transaction;
    }

    @Override
    public void commit(boolean required) throws SQLException {
        if (closed){
            throw new RuntimeException("Cannot commit, transaction is already closed");
        }
        if (required){
            transaction.commit();
        }
    }

    @Override
    public void rollback(boolean required) throws SQLException {
        if (!closed) {
            if (required) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void close(boolean forceRollback) {
        try {
            try {
                rollback(forceRollback);
            } finally {
                transaction.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            transaction = null;
            closed = true;
        }
    }
}
