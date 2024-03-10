package com.jayus.smallMyBatis.step09.builder;

import com.jayus.smallMyBatis.step09.mapping.ParameterMapping;
import com.jayus.smallMyBatis.step09.mapping.SqlSource;
import com.jayus.smallMyBatis.step09.parsing.GenericTokenParser;
import com.jayus.smallMyBatis.step09.parsing.TokenHandler;
import com.jayus.smallMyBatis.step09.reflection.MetaClass;
import com.jayus.smallMyBatis.step09.reflection.MetaObject;
import com.jayus.smallMyBatis.step09.session.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * SQL 源码构建器
 */
public class SqlSourceBuilder extends BaseBuilder {

    private static Logger logger = LoggerFactory.getLogger(SqlSourceBuilder.class);

    private static final String parameterProperties = "javaType,jdbcType,mode,numericScale,resultMap,typehandler,jdbcTypeName";

    public SqlSourceBuilder(Configuration configuration) {
        super(configuration);
    }

    public SqlSource parse(String originSql, Class<?> parameterType, Map<String,Object> additionalParameters) {
        ParameterMappingTokenHandler handler = new ParameterMappingTokenHandler(configuration, parameterType, additionalParameters);
        GenericTokenParser parser = new GenericTokenParser("#{", "}", handler);
        String sql = parser.parse(originSql);
        return new StaticSqlSource(configuration,sql,handler.getParameterMappings());
    }

    private static class ParameterMappingTokenHandler extends BaseBuilder implements TokenHandler {
        private List<ParameterMapping> parameterMappings = new ArrayList<>();

        private Class<?> parameterType;

        private MetaObject metaObject;

        public ParameterMappingTokenHandler(Configuration configuration, Class<?> parameterType,
                                            Map<String,Object> additionalParameters) {
            super(configuration);
            this.parameterType = parameterType;
            this.metaObject = configuration.newMetaObject(additionalParameters);
        }

        public List<ParameterMapping> getParameterMappings() {
            return parameterMappings;
        }

        @Override
        public String handleToken(String content) {
            parameterMappings.add(buildParameterMapping(content));
            return "?";
        }

        /**
         * 构建参数映射
         * @param content
         * @return
         */
        private ParameterMapping buildParameterMapping(String content){
            Map<String,String> propertiesMap = new ParameterExpression(content);
            String property = propertiesMap.get("property");
            Class<?> propertyType;
            if (typeHandlerRegistry.hasTypeHandler(parameterType)){
                propertyType = parameterType;
            } else if (property != null){
                MetaClass metaClass = MetaClass.forClass(parameterType);
                if (metaClass.hasGetter(property)){
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
