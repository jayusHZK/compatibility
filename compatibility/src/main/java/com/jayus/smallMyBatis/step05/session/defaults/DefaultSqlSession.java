package com.jayus.smallMyBatis.step05.session.defaults;

import com.jayus.smallMyBatis.step05.mapping.BoundSql;
import com.jayus.smallMyBatis.step05.mapping.Environment;
import com.jayus.smallMyBatis.step05.mapping.MappedStatement;
import com.jayus.smallMyBatis.step05.session.Configuration;
import com.jayus.smallMyBatis.step05.session.SqlSession;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DefaultSqlSession implements SqlSession {

    /**
     * 配置类
     */
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
            // 通过sqlID(对象类路径+方法名)获取到对应的MappedStatement对象
            MappedStatement mappedStatement = configuration.getMappedStatement(statement);
            // 获取环境配置
            Environment environment = configuration.getEnvironment();
            // 获取数据源及连接
            Connection connection = environment.getDataSource().getConnection();
            // 获取绑定 SQL 对象
            BoundSql boundSql = mappedStatement.getBoundSql();
            PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSql());
            preparedStatement.setLong(1,Long.parseLong(parameter[0].toString()));
            ResultSet resultSet = preparedStatement.executeQuery();
            List<T> res = resultSetToObj(resultSet,Class.forName(boundSql.getResultType()));

            return res.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 结果集转换为对象
     * @param <T>
     * @param resultSet sql执行的结果集
     * @param type 要转换的对象类型
     * @return
     */
    private <T> List<T> resultSetToObj(ResultSet resultSet, Class<?> type) {
        List<T> list = new ArrayList<>();

        try {
            //获取结果集合的元数据集
            ResultSetMetaData metaData = resultSet.getMetaData();
            //获取结果集合的列数
            int columnCount = metaData.getColumnCount();
            //遍历结果集合的所有行数据
            while (resultSet.next()){
                //实例化一个指定返回类型的对象
                T obj = (T)type.newInstance();
                //遍历每一个结果集的列数据，把数据封装到指定的对象中
                for(int i=1;i<=columnCount;i++){
                    String columnName = metaData.getColumnName(i);
                    Object value = resultSet.getObject(columnName);
                    //使用反射，根据数据库表和实体的对应关系完成封装
                    Field field = type.getDeclaredField(columnName);
                    //对于私有属性，需要设置可访问
                    field.setAccessible(true);
                    field.set(obj,value);
                }
                //把封装好的对象添加到集合中
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
