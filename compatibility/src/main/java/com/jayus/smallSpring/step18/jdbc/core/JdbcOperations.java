package com.jayus.smallSpring.step18.jdbc.core;

import java.util.List;
import java.util.Map;

public interface JdbcOperations {

    <T> T execute(StatementCallback<T> action);

    void execute(String sql);

    <T> T query(String sql,ResultSetExtractor<T> res);

    <T> T query(String sql,Object[] args,ResultSetExtractor<T> rse);

    <T> List<T> query(String sql, RowMapper<T> rowMapper);

    <T> List<T> query(String sql,Object[] args,RowMapper<T> rowMapper);

    <T> T query(String sql,PreparedStatementSetter pss,ResultSetExtractor<T> rse);

    List<Map<String,Object>> queryForList(String sql);

    /**
     * 查询数据库表中某一个字段
     * @param sql
     * @param elementType
     * @return
     * @param <T>
     */
    <T> List<T> queryForList(String sql,Class<T> elementType);

    <T> List<T> queryForList(String sql,Class<T> elementType,Object... args);

    List<Map<String,Object>> queryForList(String sql,Object... args);

    <T> T queryForObject(String sql,RowMapper<T> rowMapper);

    <T> T queryForObject(String sql,Object[] args,RowMapper<T> rowMapper );

    /*
        查询数据库表中 某一条记录的某一个字段
     */
    <T> T queryForObject(String sql,Class<T> requiredType);

    Map<String,Object> queryForMap(String sql);

    Map<String,Object> queryForMap(String sql,Object... args);

}
