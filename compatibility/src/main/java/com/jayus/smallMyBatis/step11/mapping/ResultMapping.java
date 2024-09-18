package com.jayus.smallMyBatis.step11.mapping;

import com.jayus.smallMyBatis.step11.session.Configuration;
import com.jayus.smallMyBatis.step11.type.JdbcType;
import com.jayus.smallMyBatis.step11.type.TypeHandler;

/**
 * @ClassName ResultMapping
 * @Description: 结果映射
 * @date: 2024/5/16 01:32
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
