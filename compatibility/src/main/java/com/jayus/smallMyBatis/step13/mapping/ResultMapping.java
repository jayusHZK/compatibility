package com.jayus.smallMyBatis.step13.mapping;

import com.jayus.smallMyBatis.step13.session.Configuration;
import com.jayus.smallMyBatis.step13.type.JdbcType;
import com.jayus.smallMyBatis.step13.type.TypeHandler;

/**
 * @ClassName ResultMapping
 * @Description: 结果映射
 * @date: 2024/10/13 09:20
 */
public class ResultMapping {

    private Configuration configuraction;
    private String property;
    private String column;
    private Class<?> javaType;
    private JdbcType jdbcType;
    private TypeHandler<?> typeHandler;

    ResultMapping(){}

    public static class Builder{
        private ResultMapping resultMapping = new ResultMapping();
    }

}
