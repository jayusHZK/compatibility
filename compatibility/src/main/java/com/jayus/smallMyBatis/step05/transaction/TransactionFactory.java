package com.jayus.smallMyBatis.step05.transaction;

import com.jayus.smallMyBatis.step05.session.TransactionIsolationLevel;

import javax.sql.DataSource;
import java.sql.Connection;

public interface TransactionFactory {

    Transaction newTransaction(Connection conn);

    Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level,boolean autoCommit);

}
