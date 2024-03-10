package com.jayus.smallMyBatis.step08.mapping;

import com.jayus.smallMyBatis.step08.session.Configuration;
import com.jayus.smallMyBatis.step08.type.JdbcType;

/**
 * 参数映射 #{property,javaType=int,jdbcType=NUMERIC}
 */
public class ParameterMapping {

    private Configuration configuration;

    private String property;

    private Class<?> javaType = Object.class;

    private JdbcType jdbcType;

    public ParameterMapping() {

    }

    public static class Builder {

        private ParameterMapping parameterMapping = new ParameterMapping();

        public Builder(Configuration configuration,String property,Class<?> javaType) {
            parameterMapping.configuration = configuration;
            parameterMapping.property = property;
            parameterMapping.javaType = javaType;
        }

        public Builder javaType(Class<?> javaType) {
            parameterMapping.javaType = javaType;
            return this;
        }

        public Builder jdbcType(JdbcType jdbcType){
            parameterMapping.jdbcType = jdbcType;
            return this;
        }

        public ParameterMapping build(){
            return parameterMapping;
        }
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public String getProperty() {
        return property;
    }

    public Class<?> getJavaType() {
        return javaType;
    }

    public JdbcType getJdbcType() {
        return jdbcType;
    }
}
