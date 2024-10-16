package com.jayus.smallMyBatis.step13.mapping;

import com.jayus.smallMyBatis.step13.session.Configuration;
import com.jayus.smallMyBatis.step13.type.JdbcType;
import com.jayus.smallMyBatis.step13.type.TypeHandler;
import com.jayus.smallMyBatis.step13.type.TypeHandlerRegistry;

/**
 * @ClassName ParameterMapping
 * @Description: 参数映射
 * @date: 2024/10/13 09:10
 */
public class ParameterMapping {

    private Configuration configuraction;

    private String property;

    private Class<?> javaType = Object.class;

    private JdbcType jdbcType;

    private TypeHandler<?> typeHandler;

    private ParameterMapping() {
    }

    public static class Builder {

        private ParameterMapping parameterMapping = new ParameterMapping();

        public Builder(Configuration configuraction, String property, Class<?> javaType) {
            parameterMapping.configuraction = configuraction;
            parameterMapping.property = property;
            parameterMapping.javaType = javaType;
        }

        public Builder javaType(Class<?> javaType){
            parameterMapping.javaType = javaType;
            return this;
        }

        public Builder jdbcType(JdbcType jdbcType) {
            parameterMapping.jdbcType = jdbcType;
            return this;
        }

        public ParameterMapping build(){
            if (parameterMapping.typeHandler == null && parameterMapping.javaType != null) {
                Configuration configuraction = parameterMapping.configuraction;
                TypeHandlerRegistry typeHandlerRegistry = configuraction.getTypeHandlerRegistry();
                parameterMapping.typeHandler = typeHandlerRegistry.getTypeHandler(parameterMapping.javaType,parameterMapping.jdbcType);
            }
            return parameterMapping;
        }

    }

    public Configuration getConfiguraction() {
        return configuraction;
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
