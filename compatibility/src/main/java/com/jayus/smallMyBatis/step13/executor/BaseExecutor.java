package com.jayus.smallMyBatis.step13.executor;

import com.jayus.smallMyBatis.step13.mapping.BoundSql;
import com.jayus.smallMyBatis.step13.mapping.MappedStatement;
import com.jayus.smallMyBatis.step13.session.Configuration;
import com.jayus.smallMyBatis.step13.session.ResultHandler;
import com.jayus.smallMyBatis.step13.session.RowBounds;
import com.jayus.smallMyBatis.step13.transaction.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @ClassName BaseExecutor
 * @Description: 执行器抽象基类
 * @date: 2024/10/13 10:38
 */
public abstract class BaseExecutor implements Executor {

    private Logger logger = LoggerFactory.getLogger(BaseExecutor.class);

    protected Configuration configuraction;
    protected Transaction transaction;
    protected Executor wrapper;

    private boolean closed;

    public BaseExecutor(Configuration configuraction, Transaction transaction) {
        this.configuraction = configuraction;
        this.transaction = transaction;
        this.wrapper = this;
    }

    @Override
    public int update(MappedStatement ms, Object parameter) throws SQLException {
        return doUpdate(ms,parameter);
    }

    @Override
    public <E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) throws SQLException {
        if (closed){
            throw new RuntimeException("Executor was closed.");
        }
        return doQuery(ms,parameter,rowBounds,resultHandler,boundSql);
    }

    protected abstract int doUpdate(MappedStatement ms,Object parameter)throws SQLException;

    protected abstract <E> List<E> doQuery(MappedStatement ms,Object parameter,RowBounds rowBounds,ResultHandler resultHandler,BoundSql boundSql) throws SQLException;

    @Override
    public Transaction getTransaction() {
        if (closed) {
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
            logger.warn("Unexpected exception on closing transaction.  Cause: " + e);
        } finally {
            transaction = null;
            closed = true;
        }
    }

    protected void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
            }
        }
    }

}
