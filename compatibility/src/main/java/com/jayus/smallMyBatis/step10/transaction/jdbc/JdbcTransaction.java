package com.jayus.smallMyBatis.step10.transaction.jdbc;

import com.jayus.smallMyBatis.step10.transaction.Transaction;

/**
 * JDBC 事务，直接利用 JDBC 的 commit、rollback。依赖于数据源获得的连接来管理事务范围。
 */
public class JdbcTransaction implements Transaction {
}
