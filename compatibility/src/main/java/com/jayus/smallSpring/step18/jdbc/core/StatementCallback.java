package com.jayus.smallSpring.step18.jdbc.core;

import java.sql.SQLException;
import java.sql.Statement;

public interface StatementCallback<T> {

    T doInStatement(Statement statement) throws SQLException;

}
