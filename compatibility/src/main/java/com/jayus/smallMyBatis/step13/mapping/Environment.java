package com.jayus.smallMyBatis.step13.mapping;

import com.jayus.smallMyBatis.step13.transaction.TransactionFactory;

import javax.sql.DataSource;

/**
 * @ClassName Environment
 * @Description: 环境
 * @date: 2024/10/12 16:37
 */
public class Environment {

    private final String id;

    private final TransactionFactory transactionFactory;

    private final DataSource dataSource;

    public Environment(String id, TransactionFactory transactionFactory, DataSource dataSource) {
        this.id = id;
        this.transactionFactory = transactionFactory;
        this.dataSource = dataSource;
    }

    public static class Builder {

        private String id;

        private TransactionFactory transactionFactory;

        private DataSource dataSource;

        public Builder(String id) {
            this.id = id;
        }

        public Builder transactionFactory(TransactionFactory transactionFactory) {
            this.transactionFactory = transactionFactory;
            return this;
        }

        public Builder setDataSource(DataSource dataSource) {
            this.dataSource = dataSource;
            return this;
        }

        public String id() {
            return id;
        }

        public Environment build() {
            return new Environment(this.id, this.transactionFactory, this.dataSource);
        }
    }

    public String getId() {
        return id;
    }

    public TransactionFactory getTransactionFactory() {
        return transactionFactory;
    }

    public DataSource getDataSource() {
        return dataSource;
    }
}
