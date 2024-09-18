package com.jayus.smallMyBatis.step11.datasource.unpooled;

import com.jayus.smallMyBatis.step11.datasource.DataSourceFactory;
import com.jayus.smallMyBatis.step11.reflection.MetaObject;
import com.jayus.smallMyBatis.step11.reflection.SystemMetaObject;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @ClassName UnpooledDataSourceFactory
 * @Description: 无池化数据源工厂
 * @date: 2024/9/17 22:24
 */
public class UnpooledDataSourceFactory implements DataSourceFactory {

    protected DataSource dataSource;

    public UnpooledDataSourceFactory() {
        this.dataSource = new UnpooledDataSource();
    }

    @Override
    public void setProperties(Properties props) {
        MetaObject metaObject = SystemMetaObject.forObject(dataSource);
        for (Object key : props.keySet()) {
            String propertyName = (String) key;
            if (metaObject.hasSetter(propertyName)) {
                String value = (String) props.get(propertyName);
                Object convertValue = convertValue(metaObject, propertyName, value);
                metaObject.setValue(propertyName,convertValue);
            }
        }
    }

    @Override
    public DataSource getDataSource() {
        return dataSource;
    }

    private Object convertValue(MetaObject metaObject,String propertyName,String value) {
        Object convertedValue = value;
        Class<?> targetType = metaObject.getSetterType(propertyName);
        if (targetType == Integer.class || targetType == int.class) {
            convertedValue = Integer.valueOf(value);
        } else if (targetType == Long.class || targetType == long.class) {
            convertedValue = Long.valueOf(value);
        } else if (targetType == Boolean.class || targetType == boolean.class){
            convertedValue = Boolean.valueOf(value);
        }
        return convertedValue;
    }
}
