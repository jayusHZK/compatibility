package com.jayus.smallMyBatis.step13.scripting.defaults;

import com.alibaba.fastjson.JSON;
import com.jayus.smallMyBatis.step13.executor.parameter.ParameterHandler;
import com.jayus.smallMyBatis.step13.mapping.BoundSql;
import com.jayus.smallMyBatis.step13.mapping.MappedStatement;
import com.jayus.smallMyBatis.step13.mapping.ParameterMapping;
import com.jayus.smallMyBatis.step13.reflection.MetaObject;
import com.jayus.smallMyBatis.step13.session.Configuration;
import com.jayus.smallMyBatis.step13.type.JdbcType;
import com.jayus.smallMyBatis.step13.type.TypeHandler;
import com.jayus.smallMyBatis.step13.type.TypeHandlerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName DefaultParameterHandler
 * @Description: 默认参数处理器
 * @date: 2024/10/13 10:15
 */
public class DefaultParameterHandler implements ParameterHandler {
    
    private Logger logger = LoggerFactory.getLogger(DefaultParameterHandler.class);
    
    private final TypeHandlerRegistry typeHandlerRegistry;
    
    private final MappedStatement mappedStatement;
    
    private final Object parameterObject;
    
    private BoundSql boundSql;
    
    private Configuration configuraction;

    public DefaultParameterHandler(MappedStatement mappedStatement, Object parameterObject, BoundSql boundSql) {
        this.mappedStatement = mappedStatement;
        this.configuraction = mappedStatement.getConfiguraction();
        this.typeHandlerRegistry = mappedStatement.getConfiguraction().getTypeHandlerRegistry();
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
        if (null != parameterMappings) {
            for (int i = 0; i < parameterMappings.size(); i++) {
                ParameterMapping parameterMapping = parameterMappings.get(i);
                String propertyName = parameterMapping.getProperty();
                Object value;
                if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                    value = parameterObject;
                } else {
                    MetaObject metaObject = configuraction.newMetaObject(parameterObject);
                    value = metaObject.getValue(propertyName);
                }
                JdbcType jdbcType = parameterMapping.getJdbcType();
                logger.info("根据每个ParameterMapping中的TypeHandler设置对应的参数信息 value：{}", JSON.toJSONString(value));
                TypeHandler typeHandler = parameterMapping.getTypeHandler();
                typeHandler.setParameter(ps,i+1,value,jdbcType);
            }
        }
    }
}
