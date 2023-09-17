package com.jayus.smallMyBatis.step05.mapping;

import com.jayus.smallMyBatis.step05.session.Configuration;

/**
 * 用于保存初步解析后的 SQL 语句和参数信息
 */
public class MappedStatement {

    // 保存配置的相关信息
    private Configuration configuration;

    // 唯一标识 一般是方法名称
    private String id;

    // SQL 命令类型
    private SqlCommandType sqlCommandType;

    // 绑定的 SQL 将
    private BoundSql boundSql;

    public MappedStatement() {

    }

    public static class Builder{
        private MappedStatement mappedStatement = new MappedStatement();

        public Builder(Configuration configuration,String id,SqlCommandType sqlCommandType,BoundSql boundSql) {
            mappedStatement.configuration = configuration;
            mappedStatement.id = id;
            mappedStatement.sqlCommandType = sqlCommandType;
            mappedStatement.boundSql = boundSql;
        }

        public MappedStatement build(){
            assert mappedStatement.configuration != null;
            assert mappedStatement.id != null;
            return mappedStatement;
        }
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SqlCommandType getSqlCommandType() {
        return sqlCommandType;
    }

    public void setSqlCommandType(SqlCommandType sqlCommandType) {
        this.sqlCommandType = sqlCommandType;
    }

    public BoundSql getBoundSql() {
        return boundSql;
    }

    public void setBoundSql(BoundSql boundSql) {
        this.boundSql = boundSql;
    }
}
