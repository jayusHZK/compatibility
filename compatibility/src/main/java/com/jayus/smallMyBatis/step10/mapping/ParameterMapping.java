package com.jayus.smallMyBatis.step10.mapping;

import com.jayus.smallMyBatis.step10.session.Configuration;
import com.jayus.smallMyBatis.step10.type.JdbcType;
import com.jayus.smallMyBatis.step10.type.TypeHandler;
import com.jayus.smallMyBatis.step10.type.TypeHandlerRegistry;

/**
 * 参数映射 #{property,javatype=int,jdbcType=NUMEPIC}
 */
public class ParameterMapping {

    private Configuration configuration;

    private String property;

    private Class<?> javaType = Object.class;

    private JdbcType jdbcType;

    private TypeHandler<?> typeHandler;

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

        public Builder jdbcType(JdbcType jdbcType) {
            parameterMapping.jdbcType = jdbcType;
            return this;
        }

        public ParameterMapping build(){
            if (parameterMapping.typeHandler == null && parameterMapping.javaType != null){
                Configuration configuration = parameterMapping.getConfiguration();
                TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
                parameterMapping.typeHandler = typeHandlerRegistry.getTypeHandler(parameterMapping.javaType,parameterMapping.jdbcType);
            }
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

    public TypeHandler<?> getTypeHandler() {
        return typeHandler;
    }
}
