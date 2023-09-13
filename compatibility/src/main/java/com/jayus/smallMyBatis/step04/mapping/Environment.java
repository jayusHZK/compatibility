package com.jayus.smallMyBatis.step04.mapping;

import com.jayus.smallMyBatis.step04.transaction.TransactionFactory;

import javax.sql.DataSource;

/**
 * xml 配置文件中的 SQL 环境封装
 */
public class Environment {

    private String id;

    private TransactionFactory transactionFactory;

    private DataSource dataSource;

    public Environment(String id, TransactionFactory transactionFactory, DataSource dataSource) {
        this.id = id;
        this.transactionFactory = transactionFactory;
        this.dataSource = dataSource;
    }

    public static class Builder {
        // 环境 id
        private String id;
        // 事务工厂
        private TransactionFactory transactionFactory;
        // 数据源
        private DataSource dataSource;

        public Builder(String id) {
            this.id = id;
        }

        public Builder transactionFactory(TransactionFactory transactionFactory){
            this.transactionFactory = transactionFactory;
            return this;
        }

        public Builder dataSource(DataSource dataSource){
            this.dataSource = dataSource;
            return this;
        }

        public String id(){
            return this.id;
        }

        public Environment build(){
            return new Environment(this.id,this.transactionFactory,this.dataSource);
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
