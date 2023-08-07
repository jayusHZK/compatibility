package com.jayus.smallSpring.step18.jdbc.core;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * sql 行转换
 * @param <T>
 */
public interface RowMapper<T> {

    T mapRow(ResultSet rs,int rowNum) throws SQLException;

}
