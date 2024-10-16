package com.jayus.smallMyBatis.step12.mapping;

import com.jayus.smallMyBatis.step12.session.Configuration;
import com.jayus.smallMyBatis.step12.type.JdbcType;
import com.jayus.smallMyBatis.step12.type.TypeHandler;

/**
 * @ClassName ResultMapping
 * @Description: 结果映射
 * @date: 2024/9/25 09:18
 */
public class ResultMapping {

    private Configuration configuration;

    private String property;

    private String column;

    private Class<?> javaType;

    private JdbcType jdbcType;

    private TypeHandler<?> typeHandler;

    ResultMapping() {
    }

    public static class Builder {
        private ResultMapping resultMapping = new ResultMapping();
    }
}
