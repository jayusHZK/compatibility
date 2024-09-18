package com.jayus.smallMyBatis.step11.executor;

import com.jayus.smallMyBatis.step11.executor.statement.StatementHandler;
import com.jayus.smallMyBatis.step11.mapping.BoundSql;
import com.jayus.smallMyBatis.step11.mapping.MappedStatement;
import com.jayus.smallMyBatis.step11.session.Configuration;
import com.jayus.smallMyBatis.step11.session.ResultHandler;
import com.jayus.smallMyBatis.step11.session.RowBounds;
import com.jayus.smallMyBatis.step11.transaction.Transaction;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

/**
 * @ClassName SimpleExecutor
 * @Description: 简单执行器
 * @date: 2024/5/15 22:36
 */
public class SimpleExecutor extends BaseExecutor{

    public SimpleExecutor(Configuration configuration, Transaction transaction) {
        super(configuration, transaction);
    }

    @Override
    protected <E> List<E> doQuery(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) {
        try {
            Configuration configuration = ms.getConfiguration();
            StatementHandler handler = configuration.newStatementHandler(this, ms, parameter, rowBounds, resultHandler, boundSql);
            Connection connection = transaction.getConnection();
            // 准备语句
            Statement stmt = handler.prepare(connection);
            handler.parameterize(stmt);
            return handler.query(stmt,resultHandler);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
