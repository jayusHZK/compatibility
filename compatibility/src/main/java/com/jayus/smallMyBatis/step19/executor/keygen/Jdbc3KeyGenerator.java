package com.jayus.smallMyBatis.step19.executor.keygen;

import com.jayus.smallMyBatis.step19.executor.Executor;
import com.jayus.smallMyBatis.step19.mapping.MappedStatement;
import com.jayus.smallMyBatis.step19.reflection.MetaObject;
import com.jayus.smallMyBatis.step19.session.Configuration;
import com.jayus.smallMyBatis.step19.type.TypeHandler;
import com.jayus.smallMyBatis.step19.type.TypeHandlerRegistry;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @ClassName Jdbc3KeyGenerator
 * @Description: 使用 JDBC3 Statement.getGeneratedKeys
 * @date: 2024/10/20 14:46
 */
public class Jdbc3KeyGenerator implements KeyGenerator {

    @Override
    public void processBefore(Executor executor, MappedStatement ms, Statement stmt, Object parameter) {

    }

    @Override
    public void processAfter(Executor executor, MappedStatement ms, Statement stmt, Object parameter) {

    }

    public void processBatch(MappedStatement ms, Statement stmt, List<Object> parameters) {
        try (ResultSet rs = stmt.getGeneratedKeys()){
            final Configuration configuration = ms.getConfiguration();
            final TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            final String[] keyProperties = ms.getKeyProperties();
            final ResultSetMetaData rsmd = rs.getMetaData();
            TypeHandler<?>[] typeHandlers = null;
            if (keyProperties != null && rsmd.getColumnCount() >= keyProperties.length) {
                for (Object parameter : parameters) {
                    if (!rs.next()) {
                        break;
                    }
                    final MetaObject metaParam = configuration.newMetaObject(parameter);
                    if (typeHandlers == null) {
                        typeHandlers = getTypeHandler(typeHandlerRegistry,metaParam,keyProperties);
                    }
                    // 填充键值
                    populateKeys(rs,metaParam,keyProperties,typeHandlers);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error getting generated key or setting result to parameter object. Cause: " + e, e);
        }
    }

    private TypeHandler<?>[] getTypeHandler(TypeHandlerRegistry typeHandlerRegistry,MetaObject metaParam,String[] keyProperties) {
        TypeHandler<?>[] typeHandlers = new TypeHandler<?>[keyProperties.length];
        for (int i = 0; i < keyProperties.length; i++) {
            if (metaParam.hasSetter(keyProperties[i])) {
                Class<?> keyPropertyType = metaParam.getSetterType(keyProperties[i]);
                TypeHandler<?> th = typeHandlerRegistry.getTypeHandler(keyPropertyType,null);
                typeHandlers[i] = th;
            }
        }
        return typeHandlers;
    }

    private void populateKeys(ResultSet rs,MetaObject metaParam,String[] keyProperties,TypeHandler<?>[] typeHandlers) throws SQLException {
        for (int i = 0; i < keyProperties.length; i++) {
            TypeHandler<?> th = typeHandlers[i];
            if (th != null) {
                Object value = th.getResult(rs, i + 1);
                metaParam.setValue(keyProperties[i],value );
            }
        }
    }

}
