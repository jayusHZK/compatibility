package com.jayus.smallMyBatis.step04.session.defaults;

import com.jayus.smallMyBatis.step04.mapping.BoundSql;
import com.jayus.smallMyBatis.step04.mapping.Environment;
import com.jayus.smallMyBatis.step04.mapping.MappedStatement;
import com.jayus.smallMyBatis.step04.session.Configuration;
import com.jayus.smallMyBatis.step04.session.SqlSession;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DefaultSqlSession implements SqlSession {

    private final Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> T selectOne(String statement) {
        return (T) ("你被代理了！，方法"+statement);
    }

    @Override
    public <T> T selectOne(String statement, Object[] parameter) {
        try {
            MappedStatement mappedStatement = configuration.getMappedStatement(statement);
            Environment environment = configuration.getEnvironment();
            Connection connection = environment.getDataSource().getConnection();
            BoundSql boundSql = mappedStatement.getBoundSql();
            // SQL 预编译
            PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSql());
            preparedStatement.setLong(1,Long.parseLong(parameter[0].toString()));
            ResultSet resultSet = preparedStatement.executeQuery();
            List<T> res = resultSetToObj(resultSet, Class.forName(boundSql.getResultType()));
            return res.get(0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 结果集转对象
     * @param resultSet
     * @param type
     * @return
     * @param <T>
     */
    private <T>List<T> resultSetToObj(ResultSet resultSet,Class<?> type){
        List<T> list = new ArrayList<>();

        try {
            // 获取结果集合的元数据集
            ResultSetMetaData metaData = resultSet.getMetaData();
            // 获取结果集合的行数
            int columnCount = metaData.getColumnCount();
            while (resultSet.next()){
                T obj = (T) type.newInstance();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object value = resultSet.getObject(columnName);
                    Field field = type.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(obj,value);
                }
                list.add(obj);
            }
        } catch (SQLException | InstantiationException | IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return configuration.getMapper(type,this);
    }
}
