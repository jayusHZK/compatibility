package com.jayus.smallMyBatis.step04.mapping;

import java.util.HashMap;
import java.util.Map;

/**
 * 绑定的 SQL 是从 SqlSource 而来 将动态内容都处理完成得到 SQL 语句字符串 其中包括 ？ 还有绑定的参数信息
 */
public class BoundSql {

    private String sql;

    private Map<Integer,String> parameterMappings = new HashMap<>();

    private String parameterType;

    private String resultType;

    public BoundSql(String sql, Map<Integer, String> parameterMappings, String parameterType, String resultType) {
        this.sql = sql;
        this.parameterMappings = parameterMappings;
        this.parameterType = parameterType;
        this.resultType = resultType;
    }

    public String getSql() {
        return sql;
    }

    public Map<Integer, String> getParameterMappings() {
        return parameterMappings;
    }

    public String getParameterType() {
        return parameterType;
    }

    public String getResultType() {
        return resultType;
    }
}
