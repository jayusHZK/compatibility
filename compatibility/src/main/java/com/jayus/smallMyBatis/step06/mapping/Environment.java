package com.jayus.smallMyBatis.step06.mapping;

import com.jayus.smallMyBatis.step06.mapping.transaction.TransactionFactory;

import javax.sql.DataSource;

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
        private String id;

        private TransactionFactory transactionFactory;

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
            return id;
        }

        public Environment build(){
            return new Environment(this.id,this.transactionFactory,this.dataSource);
        }

        public Builder(String id, TransactionFactory transactionFactory, DataSource dataSource) {
            this.id = id;
            this.transactionFactory = transactionFactory;
            this.dataSource = dataSource;
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
