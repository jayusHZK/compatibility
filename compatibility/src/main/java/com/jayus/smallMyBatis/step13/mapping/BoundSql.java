package com.jayus.smallMyBatis.step13.mapping;

import com.jayus.smallMyBatis.step13.reflection.MetaObject;
import com.jayus.smallMyBatis.step13.session.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName BoundSql
 * @Description: 绑定的 sql。从 sqlsource 而来，将动态内容都处理完成得到的sql语句字符串，其中包括 ？ 还有绑定的参数
 * @date: 2024/10/13 09:07
 */
public class BoundSql {

    private String sql;

    private List<ParameterMapping> parameterMappings;

    private Object parameterObject;

    private Map<String,Object> additionalParameters;

    private MetaObject metaParameters;

    public BoundSql(Configuration configuraction, String sql, List<ParameterMapping> parameterMappings, Object parameterObject) {
        this.sql = sql;
        this.parameterMappings = parameterMappings;
        this.parameterObject = parameterObject;
        this.additionalParameters = new HashMap<>();
        this.metaParameters = configuraction.newMetaObject(additionalParameters);
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

    public void setAdditionalParameter(String name,Object value) {
        metaParameters.setValue(name,value);
    }

    public Object getAdditionalParameter(String name) {
        return metaParameters.getGetterType(name);
    }

}
