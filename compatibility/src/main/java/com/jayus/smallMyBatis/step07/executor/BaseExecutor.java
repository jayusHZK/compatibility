package com.jayus.smallMyBatis.step07.executor;

import com.jayus.smallMyBatis.step07.mapping.BoundSql;
import com.jayus.smallMyBatis.step07.mapping.MappedStatement;
import com.jayus.smallMyBatis.step07.mapping.transaction.Transaction;
import com.jayus.smallMyBatis.step07.session.Configuration;
import com.jayus.smallMyBatis.step07.session.ResultHandler;

import java.sql.SQLException;
import java.util.List;

public abstract class BaseExecutor implements Executor {

    private org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(BaseExecutor.class);

    private Configuration configuration;

    protected Transaction transaction;

    protected Executor wrapper;

    private boolean closed;

    public BaseExecutor(Configuration configuration, Transaction transaction) {
        this.configuration = configuration;
        this.transaction = transaction;
        this.wrapper = this;
    }

    @Override
    public <E> List<E> query(MappedStatement ms, Object parameter, ResultHandler resultHandler, BoundSql boundSql) throws SQLException {
        if (closed){
            throw new RuntimeException("Executor was closed.");
        }
        return doQuery(ms,parameter,resultHandler,boundSql);
    }

    protected abstract <E> List<E> doQuery(MappedStatement ms,Object parameter,ResultHandler resultHandler,BoundSql boundSql);

    @Override
    public Transaction getTransaction() {
        if (closed){
            throw new RuntimeException("Executor was closed.");
        }
        return transaction;
    }

    @Override
    public void commit(boolean required) throws SQLException {
        if (closed) {
            throw new RuntimeException("Cannot commit, transaction is already closed");
        }
        if (required){
            transaction.commit();
        }
    }

    @Override
    public void rollback(boolean required) throws SQLException {
        if (closed) {
            throw new RuntimeException("Cannot rollback, transaction is already closed");
        }
        if (required) {
            transaction.rollback();
        }
    }

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
