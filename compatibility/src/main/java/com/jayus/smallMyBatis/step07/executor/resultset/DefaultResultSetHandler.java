package com.jayus.smallMyBatis.step07.executor.resultset;

import com.jayus.smallMyBatis.step07.executor.Executor;
import com.jayus.smallMyBatis.step07.mapping.BoundSql;
import com.jayus.smallMyBatis.step07.mapping.MappedStatement;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DefaultResultSetHandler implements ResultSetHandler {

    private final BoundSql boundSql;

    public DefaultResultSetHandler(Executor executor, MappedStatement mappedStatement,BoundSql boundSql) {
        this.boundSql = boundSql;
    }

    @Override
    public <E> List<E> handlerResultSets(Statement stmt) throws SQLException {
        ResultSet resultSet = stmt.getResultSet();
        try {
            return resultSetToObj(resultSet,Class.forName(boundSql.getResultType()));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
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
}
