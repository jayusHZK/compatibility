package com.jayus.smallSpring.step18.jdbc.support;

import com.jayus.smallSpring.step18.beans.factory.InitializingBean;

import javax.sql.DataSource;

public abstract class JdbcAccessor implements InitializingBean {

    private DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    protected DataSource obtainDataSource(){
        return getDataSource();
    }

    @Override
    public void afterPropertiesSet() {
        if (null == getDataSource()){
            throw new IllegalArgumentException("Property 'datasource' is required");
        }
    }
}
