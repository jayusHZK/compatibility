package com.jayus.smallMyBatis.step10.binding;

import com.jayus.smallMyBatis.step10.mapping.SqlCommandType;
import com.jayus.smallMyBatis.step10.session.Configuration;

import java.lang.reflect.Method;

/**
 * 映射器方法
 */
public class MapperMethod {

    private final sqlc;


    public static class SqlCommand {

        private final String name;

        private final SqlCommandType type;

        public SqlCommandType(Configuration configuration, Class<?> mapperInterface, Method method){
            String statementName = mapperInterface.getName() + "." + method.getName();
            configuration.get
        }
    }
}
