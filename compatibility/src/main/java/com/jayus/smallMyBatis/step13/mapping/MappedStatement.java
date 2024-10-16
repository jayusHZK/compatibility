package com.jayus.smallMyBatis.step13.mapping;

import com.jayus.smallMyBatis.step13.scripting.LanguageDriver;
import com.jayus.smallMyBatis.step13.session.Configuration;

import java.util.List;

/**
 * @ClassName MappedStatement
 * @Description: 映射语句类
 * @date: 2024/10/13 09:27
 */
public class MappedStatement {

    private Configuration configuraction;

    private String id;

    private SqlCommandType sqlCommandType;

    private SqlSource sqlSource;

    Class<?> resultType;

    private LanguageDriver lang;

    private List<ResultMap> resultMaps;

    MappedStatement() {
    }

    public BoundSql getBoundSql(Object parameterObject){
        return sqlSource.getBoundSql(parameterObject);
    }

    public static class Builder {

        private MappedStatement mappedStatement = new MappedStatement();

        public Builder(Configuration configuraction, String id, SqlCommandType sqlCommandType, SqlSource sqlSource, Class<?> resultType) {
            mappedStatement.configuraction = configuraction;
            mappedStatement.id = id;
            mappedStatement.sqlCommandType = sqlCommandType;
            mappedStatement.sqlSource = sqlSource;
            mappedStatement.resultType = resultType;
            mappedStatement.lang = configuraction.getDefaultScriptingLanguageInstance();
        }

        public MappedStatement build(){
            assert mappedStatement.configuraction != null;
            assert mappedStatement.id != null;
            return mappedStatement;
        }

        public String id(){
            return mappedStatement.id;
        }

        public Builder resultMaps(List<ResultMap> resultMaps) {
            mappedStatement.resultMaps = resultMaps;
            return this;
        }

    }

    public Configuration getConfiguraction() {
        return configuraction;
    }

    public String getId() {
        return id;
    }

    public SqlCommandType getSqlCommandType() {
        return sqlCommandType;
    }

    public SqlSource getSqlSource() {
        return sqlSource;
    }

    public Class<?> getResultType() {
        return resultType;
    }

    public LanguageDriver getLang() {
        return lang;
    }

    public List<ResultMap> getResultMaps() {
        return resultMaps;
    }
}
