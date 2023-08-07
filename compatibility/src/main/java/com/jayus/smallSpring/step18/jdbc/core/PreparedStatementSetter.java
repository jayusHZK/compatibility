package com.jayus.smallSpring.step18.jdbc.core;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface PreparedStatementSetter {

    void setValues(PreparedStatement ps) throws SQLException;

}
