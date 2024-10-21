package com.jayus.smallMyBatis.step19.mapping;

import com.jayus.smallMyBatis.step19.reflection.MetaObject;
import com.jayus.smallMyBatis.step19.session.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName BoundSql
 * @Description: 绑定的 SQL，是从SqlSource而来，将动态内容都处理完成得到的SQL语句字符串，其中包括?,还有绑定的参数
 * @date: 2024/10/18 11:31
 */
public class BoundSql {

    private String sql;

    private List<ParameterMapping> parameterMappings;

    private Object parameterObject;

    private Map<String,Object> additionalParameters;

    private MetaObject metaParameters;

    public BoundSql(Configuration configuration,String sql, List<ParameterMapping> parameterMappings, Object parameterObject) {
        this.sql = sql;
        this.parameterMappings = parameterMappings;
        this.parameterObject = parameterObject;
        this.additionalParameters = new HashMap<>();
        this.metaParameters = configuration.newMetaObject(additionalParameters);
    }

    public String getSql() {
        return sql;
    }

    public List<ParameterMapping> getParameterMappings() {
        return parameterMappings;
    }

    public Object getParameterObject() {
        return parameterObject;
    }

    public boolean hasAdditionalParameter(String name) {
        return metaParameters.hasGetter(name);
    }

    public void setAdditionalParameters(String name,Object value) {
        metaParameters.setValue(name,value);
    }

    public Object getAdditionalParameter(String name) {
        return metaParameters.getValue(name);
    }
}
