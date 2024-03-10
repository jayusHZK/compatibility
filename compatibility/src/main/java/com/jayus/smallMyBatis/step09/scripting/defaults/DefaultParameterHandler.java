package com.jayus.smallMyBatis.step09.scripting.defaults;

import com.alibaba.fastjson.JSON;
import com.jayus.smallMyBatis.step09.executor.parameter.ParameterHandler;
import com.jayus.smallMyBatis.step09.mapping.BoundSql;
import com.jayus.smallMyBatis.step09.mapping.MappedStatement;
import com.jayus.smallMyBatis.step09.mapping.ParameterMapping;
import com.jayus.smallMyBatis.step09.reflection.MetaObject;
import com.jayus.smallMyBatis.step09.session.Configuration;
import com.jayus.smallMyBatis.step09.type.JdbcType;
import com.jayus.smallMyBatis.step09.type.TypeHandler;
import com.jayus.smallMyBatis.step09.type.TypeHandlerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * 默认参数处理器
 */
public class DefaultParameterHandler implements ParameterHandler {

    private Logger logger = LoggerFactory.getLogger(DefaultParameterHandler.class);

    private TypeHandlerRegistry typeHandlerRegistry;

    private final MappedStatement mappedStatement;

    private final Object parameterObject;

    private BoundSql boundSql;

    private Configuration configuration;

    public DefaultParameterHandler(MappedStatement mappedStatement,Object parameterObject, BoundSql boundSql) {
        this.mappedStatement = mappedStatement;
        this.configuration = mappedStatement.getConfiguration();
        this.typeHandlerRegistry = mappedStatement.getConfiguration().getTypeHandlerRegistry();
        this.parameterObject = parameterObject;
        this.boundSql = boundSql;
    }

    @Override
    public Object getParameterObject() {
        return parameterObject;
    }

    @Override
    public void setParameters(PreparedStatement ps) throws SQLException {
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        if (null != parameterMappings){
            for (int i = 0; i < parameterMappings.size(); i++) {
                ParameterMapping parameterMapping = parameterMappings.get(i);
                String propertyName = parameterMapping.getProperty();
                Object value;
                if (typeHandlerRegistry.hasTypeHandler(parameterMapping.getClass())){
                    value = parameterMapping;
                } else {
                    // 通过 metaObject.getValue 反射取得值设进去
                    MetaObject metaObject = configuration.newMetaObject(parameterObject);
                    value = metaObject.getValue(propertyName);
                }
                JdbcType jdbcType = parameterMapping.getJdbcType();
                logger.info("根据每个ParameterMapping中的TypeHandler设置对应的参数信息 value：{}", JSON.toJSONString(value));
                TypeHandler typeHandler = parameterMapping.getTypeHandler();
                typeHandler.setParamenter(ps,i+1,value,jdbcType);
            }
        }

    }
}
