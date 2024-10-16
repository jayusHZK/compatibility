package com.jayus.smallMyBatis.step12.builder;

import com.jayus.smallMyBatis.step12.mapping.ParameterMapping;
import com.jayus.smallMyBatis.step12.mapping.SqlSource;
import com.jayus.smallMyBatis.step12.parsing.GenericTokenParser;
import com.jayus.smallMyBatis.step12.parsing.TokenHandler;
import com.jayus.smallMyBatis.step12.reflection.MetaClass;
import com.jayus.smallMyBatis.step12.reflection.MetaObject;
import com.jayus.smallMyBatis.step12.session.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName SqlSourceBuilder
 * @Description: SQL 源码构建器
 * @date: 2024/9/26 01:17
 */
public class SqlSourceBuilder extends BaseBuilder {

    private static Logger logger = LoggerFactory.getLogger(SqlSourceBuilder.class);

    private static final String parameterProperties = "javaType,jdbcType,mode,numericScale,resultMap,typeHandler,jdbcTypeName";

    public SqlSourceBuilder(Configuration configuration) {
        super(configuration);
    }

    public SqlSource parse(String originalSql, Class<?> parameterType, Map<String,Object> additionalParameters) {
        ParameterMappingTokenHander handler = new ParameterMappingTokenHander(configuration,  parameterType, additionalParameters);
        GenericTokenParser parser = new GenericTokenParser("#{", "}", handler);
        String sql = parser.parse(originalSql);
        return new StaticSqlSource(configuration,sql,handler.getParameterMappings());
    }

    private static class ParameterMappingTokenHander extends BaseBuilder implements TokenHandler {

        public List<ParameterMapping> parameterMappings = new ArrayList<>();

        private Class<?> parameterType;

        private MetaObject metaParameters;

        public ParameterMappingTokenHander(Configuration configuration, Class<?> parameterType,Map<String,Object> additionalParameters) {
            super(configuration);
            this.parameterType = parameterType;
            this.metaParameters = configuration.newMetaObject(additionalParameters);
        }

        public List<ParameterMapping> getParameterMappings() {
            return parameterMappings;
        }

        @Override
        public String handleToken(String content) {
            parameterMappings.add(buildParameterMapping(content));
            return "?";
        }

        private ParameterMapping buildParameterMapping(String content) {
            ParameterExpression propertiesMap = new ParameterExpression(content);
            String property = propertiesMap.get("property");
            Class<?> propertyType;
            if (typeHandlerRegistry.hasTypeHandler(parameterType)) {
                propertyType = parameterType;
            } else if (property != null) {
                MetaClass metaClass = MetaClass.forClass(parameterType);
                if (metaClass.hasGetter(property)) {
                    propertyType = metaClass.getGetterType(property);
                } else {
                    propertyType = Object.class;
                }
            } else {
                propertyType = Object.class;
            }
            logger.info("构建参数映射 property：{} propertyType：{}", property, propertyType);
            ParameterMapping.Builder builder = new ParameterMapping.Builder(configuration, property, propertyType);
            return builder.build();
        }

    }

}
