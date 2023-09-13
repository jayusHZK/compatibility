package com.jayus.smallMyBatis.step04.mapping;

import com.jayus.smallMyBatis.step04.session.Configuration;

/**
 * 用于保存初步解析的 SQL 语句和参数信息
 */
public class MappedStatement {

    /**
     * 相关配置信息
     */
    private Configuration configuration;

    /**
     * 唯一标识 一般是方法名称
     */
    private String id;

    // SQL 类型
    private SqlCommandType sqlCommandType;

    // 绑定的 SQL 将动态内容都处理完成得到的 SQL 语句字符串 其中包括？ 还有绑定的参数信息
    private BoundSql boundSql;

    private MappedStatement(){

    }

    public static class Builder {

        private MappedStatement mappedStatement = new MappedStatement();

        public Builder(Configuration configuration,String id,SqlCommandType sqlCommandType,BoundSql boundSql){
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
