package com.jayus.smallMyBatis.step10.biulder;

import com.jayus.smallMyBatis.step10.mapping.ParameterMapping;
import com.jayus.smallMyBatis.step10.mapping.SqlSource;
import com.jayus.smallMyBatis.step10.parsing.TokenHandler;
import com.jayus.smallMyBatis.step10.reflection.MetaObject;
import com.jayus.smallMyBatis.step10.session.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * sql 源码构建器
 */
public class SqlSourceBuilder extends BaseBuilder{

    private static Logger logger = LoggerFactory.getLogger(SqlSourceBuilder.class);

    private static final String parameterProperties = "javaType,jdbcType,mode,numericScale,resultMap,typeHandler,jdbcTypeName";

    public SqlSourceBuilder(Configuration configuration) {
        super(configuration);
    }

    public SqlSource parse(String originSql, Class<?> parameterType, Map<String,Object> additionalParameters) {
        new
    }

    private static class ParameterMappingTokenHandler extends BaseBuilder implements TokenHandler {

        private List<ParameterMapping> parameterMappings = new ArrayList<>();

        private Class<?> parameterType;

        private MetaObject metaParameters;

        public ParameterMappingTokenHandler(Configuration configuration, Class<?> parameterType,Map<String,Object> additionalParameters) {
            super(configuration);
            this.parameterType = parameterType;
            this.metaParameters = configuration.newMetaObject(additionalParameters);
        }

        public List<ParameterMapping> getParameterMappings() {
            return parameterMappings;
        }

        @Override
        public String handleToken(String content) {
            parameterMappings.add()
            return null;
        }

        private ParameterMapping buildParameterMapping(String content) {
            // 先解析参数映射 就是转化成一个 hashmap
            Map<String,String> propertiesMap = new ParameterExpression(content);
            String property = propertiesMap.get("property");
            Class<?> propertyType;
            if (typeHandlerRegistry.hast)
        }
    }

}
