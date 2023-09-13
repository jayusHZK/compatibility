package com.jayus.smallMyBatis.step04.binding;

import com.jayus.smallMyBatis.step04.mapping.MappedStatement;
import com.jayus.smallMyBatis.step04.mapping.SqlCommandType;
import com.jayus.smallMyBatis.step04.session.Configuration;
import com.jayus.smallMyBatis.step04.session.SqlSession;

import java.lang.reflect.Method;

/**
 * 映射器方法
 */
public class MapperMethod {

    private final SqlCommand command;

    public MapperMethod(Class<?> mapperInterface,Method method,Configuration configuration) {
        this.command = new SqlCommand(configuration,mapperInterface,method);
    }

    public Object execute(SqlSession sqlSession,Object[] args){
        Object result = null;
        switch (command.getType()){
            case INSERT:
                break;
            case DELETE:
                break;
            case UPDATE:
                break;
            case SELECT:
                result = sqlSession.selectOne(command.getName(),args);
                break;
            default:
                throw new RuntimeException("Unknown execution method for: " + command.getName());
        }
        return result;
    }

    public static class SqlCommand {
        // SQL 指令名称
        private final String name;

        // SQL 指令类型
        private final SqlCommandType type;

        public SqlCommand(Configuration configuration, Class<?> mapperInterface, Method method) {
            String statementName = mapperInterface.getName() + "." + method.getName();
            MappedStatement ms = configuration.getMappedStatement(statementName);
            name = ms.getId();
            type = ms.getSqlCommandType();
        }

        public String getName() {
            return name;
        }

        public SqlCommandType getType() {
            return type;
        }
    }




}
